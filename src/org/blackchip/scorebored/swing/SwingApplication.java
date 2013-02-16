package org.blackchip.scorebored.swing;

import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import org.blackchip.scorebored.Branding;


public abstract class SwingApplication {

    private String[] args;
    
    public SwingApplication() {
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", 
                Branding.NAME);
        try {
            if ( System.getProperty("os.name").contains("Linux") ) {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;               
                    }
                }
            } else {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            }
        } catch ( Exception e ) { 
            System.err.println("WARNING: Unable to set look and feel: " + 
                    e.getMessage());
        }
    }
    
    public String[] getArguments() {
        return this.args;
    }
    
    public final void run(String args[]) {
        this.args = args;
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                application();
            }
        });          
    }
    
    public abstract void application();
    
}
