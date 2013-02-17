package org.blackchip.scorebored.swing;

import javax.swing.JTextArea;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.blackchip.scorebored.util.Check;


public class TextAreaAppender extends AppenderSkeleton {

    private JTextArea text;
    
    public TextAreaAppender(JTextArea text) {
        this.text = Check.notNull(text);
    }
    
    @Override
    protected void append(LoggingEvent le) {
        if ( text.getText().length() > 0 ) {
            text.append("\n");
        }
        text.append(le.getRenderedMessage());
        text.setCaretPosition(text.getText().length() - 1);
    }

    @Override
    public void close() {
    }

    @Override
    public boolean requiresLayout() {
        return true;
    }
    
}
