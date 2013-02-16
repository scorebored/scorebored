package org.blackchip.scorebored.talker;

import org.blackchip.scorebored.util.Check;
import org.blackchip.scorebored.util.Strings;


public class Speech {

    /**
     * Text to be shown when read.
     */
    private String displayAs;
    
    /**
     * Text to be used to speak.
     */
    private String speakAs;
    
    private Speech() {
    }
    
    public Speech(String text) {
        this.displayAs = Check.notNull(text);
        this.speakAs = Check.notNull(text);
    }
    
    public Speech(String displayAs, String speakAs) {
        this.displayAs = Check.notNull(displayAs);
        if ( Strings.isEmpty(speakAs) ) {
            this.speakAs = this.displayAs;
        } else {
            this.speakAs = speakAs;
        }
    }
    
    public String getDisplayAs() {
        return this.displayAs;
    }
    
    public String getSpeakAs() {
        return this.speakAs;
    }
    
    @Override
    public String toString() {
        return getDisplayAs();
    }
    
    public static Speech[] fromArray(String... sentences) {
        Speech speech[] = new Speech[sentences.length];
        for ( int i = 0; i < sentences.length; i++ ) {
            speech[i] = new Speech(sentences[i]);
        }
        return speech;
    }
}
