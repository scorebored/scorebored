package us.praefectus.scorebored.swing;

import java.awt.*;
import javax.swing.JOptionPane;
import org.apache.log4j.Logger;
import us.praefectus.scorebored.util.Strings;

public class Swing {
    
    private static final Logger log = Logger.getLogger(Swing.class);
    
    static final GraphicsEnvironment env = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        
    public static void centerOnScreen(Window w) { 
        GraphicsDevice device = env.getDefaultScreenDevice();
        DisplayMode mode = device.getDisplayMode();
    
        w.setBounds((mode.getWidth() / 2) - (w.getWidth() / 2),
                    (mode.getHeight() / 2) - (w.getHeight() / 2), 
                    w.getWidth(), w.getHeight());
    }
    
    public static void fillToScreen(Window w) {
        GraphicsDevice device = env.getDefaultScreenDevice();
        DisplayMode mode = device.getDisplayMode();
        w.setBounds(0, 0, mode.getWidth(), mode.getHeight());
    }
    
    public static void showWarning(String message) { 
        JOptionPane.showMessageDialog(null, message, "Warning", 
                JOptionPane.WARNING_MESSAGE);
    }
    
    public static void showError(String message, Throwable t) { 
        log.error(message, t);
        Window fullScreenWindow = 
                env.getDefaultScreenDevice().getFullScreenWindow();
        if ( fullScreenWindow != null ) { 
             env.getDefaultScreenDevice().setFullScreenWindow(null);
        }
        boolean done = false;
        while ( !done ) {
            int value = JOptionPane.showOptionDialog(
                    null, 
                    message + "\n\n" + Strings.wrapText(80, t.getMessage()), 
                    "Error", 
                    0, 
                    JOptionPane.ERROR_MESSAGE, 
                    null,
                    new Object[] { "Continue", "Show Details", "Abort" }, 
                    "Continue");
            switch ( value ) {
                case 0: // Continue
                    done = true;
                    break;
                case 1: // Show details
                    StackTraceDialog dialog = new StackTraceDialog(t);
                    Swing.centerOnScreen(dialog);
                    dialog.setVisible(true);
                    break;
                case 2: // Abort
                    System.exit(1);
            }
        }

        if ( fullScreenWindow != null ) {
             env.getDefaultScreenDevice().setFullScreenWindow(fullScreenWindow);   
        }
    }
    
    public static void showError(Throwable t) {
        showError("An unexpected error has occured.", t);
    }
}
