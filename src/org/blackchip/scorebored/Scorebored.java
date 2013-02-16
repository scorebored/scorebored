package org.blackchip.scorebored;

import java.awt.Image;
import java.io.File;
import javax.swing.ImageIcon;


public class Scorebored {

    private static final ImageIcon icon = 
            new ImageIcon(Scorebored.class.getResource("/icons/paddle-transparent.png"));
    
    private Scorebored() {
    }
    
    public static final File CONFIG_DIR = new File(
            System.getProperty("user.home") + File.separator + 
            ".pong-scorebored");
    
    public static Image getIconImage() {
        return icon.getImage();
    }
}
