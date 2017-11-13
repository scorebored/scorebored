package org.blackchip.scorebored.swing;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import org.blackchip.scorebored.Main;
import org.blackchip.scorebored.util.IO;
import org.blackchip.scorebored.util.ResourceException;

public class FontLoader {

    private static final GraphicsEnvironment env = 
            GraphicsEnvironment.getLocalGraphicsEnvironment();
        
    public FontLoader() {
    }
    
    public void load(FontResource resource) throws ResourceException {
        InputStream in = null; 
        try {
            in = Main.class.getResourceAsStream(resource.getPath());
            Font font = Font.createFont(resource.getType(), in);
            env.registerFont(font);
        } catch ( FontFormatException ffe ) { 
            throw new ResourceException("Invalid font format for " + 
                    resource.getPath() + ": " + ffe.getMessage(), ffe);
        } catch ( IOException ie ) {
            throw new ResourceException("Unable to load resource " + 
                    resource.getPath() + ": " + ie.getMessage(), ie);
        } finally {
            IO.release(in);
        }
    }
}
