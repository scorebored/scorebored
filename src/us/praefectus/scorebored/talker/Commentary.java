package us.praefectus.scorebored.talker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Commentary {

    private List<Speech> sentences = new ArrayList<Speech>();
    
    public Commentary() {
        next("");
    }
    
    public Commentary add(String sentence) {
        return add(new Speech(sentence));
    }
    
    public Commentary add(String displayAs, String speakAs) {
        return add(new Speech(displayAs, speakAs));
    }
    
    public Commentary add(Speech sentence) {
        int last = sentences.size() - 1;
        Speech old = sentences.get(last);
        Speech speech = new Speech(
                old.getDisplayAs() + sentence.getDisplayAs(),
                old.getSpeakAs() + sentence.getSpeakAs());
        sentences.set(last, speech);
        return this;
    }
    
    public Commentary next(String sentence) {
        return next(new Speech(sentence));
    }
    
    public Commentary next(String displayAs, String speakAs) { 
        return next(new Speech(displayAs, speakAs));
    }
    
    public Commentary next(Speech sentence) {
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
