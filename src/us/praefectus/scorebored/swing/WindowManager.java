/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package us.praefectus.scorebored.swing;

import java.awt.Window;
import java.util.HashMap;

/**
 *
 * @author mcgann
 */
public class WindowManager {
   
    private static final WindowManager instance = new WindowManager();
    
    private HashMap<Class<? extends Window>, Window> windows = 
            new HashMap<Class<? extends Window>, Window>();
    
    private WindowManager() {
    }
    
    public static WindowManager getInstance() {
        return instance;
    }
    
    public void register(Window window) {
        if ( windows.containsKey(window.getClass()) ) {
            throw new IllegalArgumentException("Window already registered: " + 
                    window.getClass().getName());
        }
        windows.put(window.getClass(), window);
    }
    
    public Window get(Class<? extends Window> windowType) {
        Window window = windows.get(windowType);
        return window;
    }
}
