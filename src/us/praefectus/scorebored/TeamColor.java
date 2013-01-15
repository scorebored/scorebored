package us.praefectus.scorebored;

import java.awt.Color;

public enum TeamColor {

    LED_RED("Red", new Color(255, 192, 192)),
    LED_CYAN("Blue", new Color(192, 192, 255)),
    LED_GREEN("Green", new Color(192, 255, 192)), 
    LED_AMBER("Amber", new Color(255, 255, 192)),
    LCD("LCD", new Color(107, 109, 97));
    
    private String name;
    private Color color;
    
    TeamColor(String name, Color color) {
        this.name = name;
        this.color = color;
    }
    
    public String getName() {
        return name;
    }
    
    public Color getColor() {
        return color;
    }
    
    @Override
    public String toString() {
        return name;
    }
}
