package us.praefectus.scorebored.swing;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import javax.swing.Timer;
import us.praefectus.scorebored.util.Check;
import us.praefectus.scorebored.util.Strings;

public class TextRenderer {

    private String text = null;
    private Component parent ;
    private float anchorWidth;
    private float anchorHeight;
    private Color color = Color.BLACK;
    private Color effectiveColor = Color.BLACK;
    private Font font = new Font("Serif", Font.BOLD, 32);
    private Timer fadeTimer;
    private long fadeStartTime;
    private long fadeTime;
    
    public TextRenderer(Component parent, float anchorWidth, 
            float anchorHeight) {        
        this.parent = Check.notNull(parent);
        this.anchorWidth = anchorWidth;
        this.anchorHeight = anchorHeight;
        
        fadeTimer = new Timer(30, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                doFade();
            }
        });
        fadeTimer.setRepeats(true);
    }
    
    public TextRenderer(Component parent) {
        this(parent, 0.5f, 0.5f);
    }
    
    public float getAnchorWidth() {
        return anchorWidth;
    }
    
    public float getAnchorHeight() {
        return anchorHeight;
    }
    
    public void setAnchor(float anchorWidth, float anchorHeight) { 
        this.anchorWidth = anchorWidth;
        this.anchorHeight = anchorHeight;
    }

    public Font getFont() {
        return this.font;
    }
    
    public void setFont(Font font) { 
        this.font = Check.notNull(font);
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(Color color) { 
        this.color = Check.notNull(color);
        this.effectiveColor = this.color;
    }
    
    public String getText() {
        return text;
    }
    
    public void setText(String text) { 
        fadeTimer.stop();
        effectiveColor = color;
        this.text = text;
    }
    
    public void fade(long sustain, long decay) { 
        if ( fadeTimer != null ) { 
            fadeTimer.stop();
        }
        fadeStartTime = System.currentTimeMillis() + sustain;
        fadeTime = fadeStartTime + decay;
        fadeTimer.start();
    }
    
    public void fade(long decay) { 
        fade(0, decay);
    }
    
    private void doFade() {
        long now = System.currentTimeMillis();
        long delta = fadeTime - now;
        if ( delta < 0 ) { 
            fadeTimer.stop();
            setText(null);
            effectiveColor = color;
            parent.repaint();
            return;
        }
        float alpha = delta / (float)(fadeTime - fadeStartTime);
        if ( alpha > 1 ) { 
            alpha = 1;
        }
        effectiveColor = new Color(color.getRed(), color.getGreen(), 
                color.getBlue(), (int)(alpha * 255));
        parent.repaint();
    }
    
    public void paint(Graphics g1) { 
        if ( Strings.isEmpty(text) ) { 
            return;
        }
        Graphics2D g = (Graphics2D)g1;
        g.setFont(font);
        g.setColor(effectiveColor);
        Rectangle2D bounds = font.getStringBounds(text, 
                g.getFontRenderContext());
        g.drawString(text, 
                     (int)(parent.getWidth() * anchorWidth - bounds.getWidth() / 2),
                     (int)(parent.getHeight() * anchorHeight - bounds.getHeight() / 2));
    }
}
