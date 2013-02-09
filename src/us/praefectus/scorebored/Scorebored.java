package us.praefectus.scorebored;

import java.io.File;


public class Scorebored {

    private Scorebored() {
    }
    
    public static final File CONFIG_DIR = new File(
            System.getProperty("user.home") + File.separator + 
            ".pong-scorebored");
    
}
