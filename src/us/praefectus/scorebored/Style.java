package us.praefectus.scorebored;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Style {
    
    public static final Style LED;
    public static final Style LCD;
    
    private static final List<Style> STYLES;
    
    private String name;
    private Color backgroundColor = Color.BLACK;
    private Color buttonTextColor = Color.GRAY;
    private Color buttonHoverColor = Color.WHITE;
    private Color textBackgroundColor = Color.WHITE;
    private Color subtitleColor = Color.WHITE; 
    private Font teamFont = new Font("Inconsolata", Font.BOLD, 75);
    private Font scoreFont = new Font("Inconsolata", Font.BOLD, 450);
    private long subtitleFadeTime = 1000;
    
    private List<TeamColor> teamColors;
    
    static {
        LED = new Style();
        LED.name = "LED";
        LED.backgroundColor = new Color(8, 8, 8);
        LED.textBackgroundColor = new Color(42, 42, 42);
        LED.buttonTextColor = Color.LIGHT_GRAY; 
        LED.buttonHoverColor = Color.YELLOW;
        LED.subtitleColor = Color.WHITE;
        LED.scoreFont = new Font("DS-Digital", Font.PLAIN, 550);
        LED.teamColors = Collections.unmodifiableList(Arrays.asList(
                TeamColor.LED_RED, 
                TeamColor.LED_CYAN,
                TeamColor.LED_AMBER,
                TeamColor.LED_GREEN
        ));
        
        LCD = new Style();
        LCD.name = "LCD";
        LCD.backgroundColor = new Color(235, 238, 220);
        LCD.textBackgroundColor = new Color(255, 255, 250);
        LCD.buttonTextColor = TeamColor.LCD.getColor();
        LCD.buttonHoverColor = LCD.buttonTextColor;
        LCD.subtitleColor = TeamColor.LCD.getColor();
        LCD.scoreFont = new Font("DS-Digital", Font.PLAIN, 550);
        LCD.teamColors = Collections.unmodifiableList(Arrays.asList(
                TeamColor.LCD
        ));
        
        STYLES = Collections.unmodifiableList(Arrays.asList(
                LED,
                LCD
        ));
    }
    
    public static List<Style> getStyles() {
        return STYLES;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the backgroundColor
     */
    public Color getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * @return the teamColors
     */
    public List<TeamColor> getTeamColors() {
        return teamColors;
    }
    
    @Override
    public String toString() {
        return name;
    }

    /**
     * @return the buttonTextColor
     */
    public Color getButtonTextColor() {
        return buttonTextColor;
    }

    /**
     * @param buttonTextColor the buttonTextColor to set
     */
    public void setButtonTextColor(Color buttonTextColor) {
        this.buttonTextColor = buttonTextColor;
    }

    /**
     * @return the buttonHoverColor
     */
    public Color getButtonHoverColor() {
        return buttonHoverColor;
    }

    /**
     * @param buttonHoverColor the buttonHoverColor to set
     */
    public void setButtonHoverColor(Color buttonHoverColor) {
        this.buttonHoverColor = buttonHoverColor;
    }

    /**
     * @return the textBackgroundColor
     */
    public Color getTextBackgroundColor() {
        return textBackgroundColor;
    }

    /**
     * @return the subtitleColor
     */
    public Color getSubtitleColor() {
        return subtitleColor;
    }
    
    public long getSubtitleFadeTime() {
        return subtitleFadeTime;
    }

    public Font getScoreFont() {
        return scoreFont;
    }
    
    public Font getTeamFont() {
        return teamFont;
    }
   
}
