package us.praefectus.scorebored.talker;

import us.praefectus.scorebored.util.Check;


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
        this.displayAs = Check.notEmpty(text);
        this.speakAs = Check.notEmpty(text);
    }
    
    public Speech(String displayAs, String speakAs) {
        this.displayAs = Check.notEmpty(displayAs);
        this.speakAs = Check.notEmpty(speakAs);
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
