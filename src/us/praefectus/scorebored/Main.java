package us.praefectus.scorebored;

import java.awt.Font;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import org.apache.log4j.Logger;
import us.praefectus.scorebored.swing.FontLoader;
import us.praefectus.scorebored.swing.FontResource;
import us.praefectus.scorebored.swing.Swing;
import us.praefectus.scorebored.swing.WindowManager;
import us.praefectus.scorebored.talker.*;
import us.praefectus.scorebored.util.ResourceException;

public class Main {
    
    private static final Logger log = Logger.getLogger(Main.class);
    
    private FontResource[] fonts = {
        new FontResource(Font.TRUETYPE_FONT, "/fonts/Inconsolata.ttf"),
        new FontResource(Font.TRUETYPE_FONT, "/fonts/DS-Digital.ttf")
    };

    private Class<? extends Talker> findTalkerClass() throws TalkException {
        Class<? extends Talker> talkerClass = null;
        String talkerClassName = System.getProperty(Branding.SLUG + ".talker");

        if ( talkerClassName != null ) {
            try {
                talkerClass =(Class<? extends Talker>)Class
                        .forName(talkerClassName);
            } catch ( ClassNotFoundException cnfe ) {
                throw new TalkException("Cannot find talker class: " + 
                        talkerClassName, cnfe);
            }
        }
        
        if ( talkerClass == null ) {
            String os = System.getProperty("os.name");
            if ( os.contains("Mac") && MacTalker.isSupported() ) { 
                talkerClass = MacTalker.class;
            }
            else if (os.contains("Linux") && LinuxTalker.isSupported() ) {
                talkerClass = LinuxTalker.class;
            }
        }
        return talkerClass;
    }
    
    private TalkerFactory createTalkerFactory() {
        Class<? extends Talker> talkerClass = null;
        try {
             talkerClass = findTalkerClass();
        } catch ( TalkException te ) { 
            Swing.showWarning("Unable to load the necessary " + 
                    "talker.\n\n" + te.getMessage() + "\n\nYou will not hear " + 
                    "any commentary during the match.");
            talkerClass = MuteTalker.class;
        }
        if ( talkerClass == null ) { 
            Swing.showWarning("No suitable talker found.\n\nYou will not " +
                    "hear any commentary during the match");
            talkerClass = MuteTalker.class;
        }
        log.debug("Talker: " + talkerClass.getName());
        return new TalkerFactory(talkerClass);        
    }
    
    public void application() {
        final SplashScreen splashScreen = new SplashScreen();
        Swing.centerOnScreen(splashScreen);
        splashScreen.setVisible(true);
        new SwingWorker<Object,Object>(){          
            @Override
            protected Object doInBackground() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {                
                }
                return null;
            }
             @Override
            protected void done() {
                 splashScreen.setVisible(false);
                 startDashBoard();
            }
        }.execute();
    }
    
    public void startDashBoard() {
        FontLoader fontLoader = new FontLoader();
        for ( FontResource font: fonts ) { 
            try {
                log.debug("Loading font: " + font.getPath());
                fontLoader.load(font);
            } catch ( ResourceException re ) {
                log.warn("Cannot load font: " + re.getMessage());
            }
        }
        TalkerFactory talkerFactory = createTalkerFactory();

        try {
            Match match = new Match(talkerFactory.newSwingTalker());

            WindowManager windowManager = WindowManager.getInstance();

            DashboardFrame dashboardFrame = new DashboardFrame(match);
            windowManager.register(dashboardFrame);
            ScoreboardFrame scoreboardFrame = new ScoreboardFrame(match);
            windowManager.register(scoreboardFrame);

            Swing.centerOnScreen(dashboardFrame);
            dashboardFrame.setVisible(true);
            log.debug("Application started");
        } catch ( Exception e ) {
            Swing.showError("Unable to start application: " + e.getMessage(), 
                    e);
        }
    }

    public static void main(String[] args) throws Exception {            
        log.debug("Application starting");
        
        try {
            if ( System.getProperty("os.name").contains("Linux") ) {
                UIManager.setLookAndFeel(UIManager
                        .getCrossPlatformLookAndFeelClassName());
                for ( UIManager.LookAndFeelInfo info : 
                        UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;               
                    }
                }
            } else {
                UIManager.setLookAndFeel(UIManager
                        .getSystemLookAndFeelClassName());
            }
            log.debug("Look and feel: " + UIManager.getLookAndFeel().getName());
        } catch ( Exception e ) { 
            log.warn("Unable to set the look and feel: " + e.getMessage());
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {               
                new Main().application();
            }
        }); 
    }

}
