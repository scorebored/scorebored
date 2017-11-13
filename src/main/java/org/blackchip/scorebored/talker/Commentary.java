package org.blackchip.scorebored.talker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Commentary {

    private List<Speech> sentences = new ArrayList<Speech>();
    
    public Commentary() {
        sentences.add(new Speech(""));
    }
    
    public Commentary add(String sentence) {
        return add(new Speech(sentence));
    }
    
    public Commentary add(String displayAs, String speakAs) {
        return add(new Speech(displayAs, speakAs));
    }
    
    public Speech getLast() {
        int last = sentences.size() - 1;
        if ( last >= 0 ) {
            return sentences.get(last);
        }
        return new Speech("");
    }
    
    public Commentary add(Speech sentence) {
        Speech old = getLast();
        Speech speech = new Speech(
                old.getDisplayAs() + sentence.getDisplayAs(),
                old.getSpeakAs() + sentence.getSpeakAs());
        sentences.set(sentences.size() - 1, speech);
        return this;
    }
    
    public Commentary next(String sentence) {
        return next(new Speech(sentence));
    }
    
    public Commentary next(String displayAs, String speakAs) { 
        return next(new Speech(displayAs, speakAs));
    }
    
    public Commentary next(Speech sentence) {
        Speech old = getLast();
        if ( old.getSpeakAs().trim().length() == 0 ) {
            add(sentence);
        } else {
            sentences.add(sentence);
        }
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
