package org.blackchip.scorebored.swing;

import org.blackchip.scorebored.util.Check;

public class FontResource {

    private int type;
    private String path;
    
    private FontResource() {
    }
    
    public FontResource(int type, String path) {
        this.type = type;
        this.path = Check.notEmpty(path);
    }
    
    public int getType() {
        return this.type;
    }
    
    public String getPath() {
        return this.path;
    }
}
