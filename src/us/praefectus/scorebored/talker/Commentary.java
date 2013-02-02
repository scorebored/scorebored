package us.praefectus.scorebored.talker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Commentary {

    private List<Speech> sentences = new ArrayList<Speech>();
    
    public Commentary() {
    }
    
    public Commentary add(String sentence) {
        sentences.add(new Speech(sentence));
        return this;
    }
    
    public Commentary add(String displayAs, String speakAs) {
        sentences.add(new Speech(displayAs, speakAs));
        return this;
    }
    
    public Commentary add(Speech sentence) {
        sentences.add(sentence);
        return this;
    }
    
    public List<Speech> getSentences() {
        return Collections.unmodifiableList(sentences);
    }
    
    public static Commentary fromArray(String[] sentences) {
        Commentary commentary = new Commentary();
        for ( String sentence: sentences ) {
            commentary.add(new Speech(sentence));
        }
        return commentary;
    }
}
