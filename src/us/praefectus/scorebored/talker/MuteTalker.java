package us.praefectus.scorebored.talker;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Logger;

/**
 * Talker that does not talk. Useful for debugging sessions when you
 * do not want to annoy your coworker in the cube next to you. Use by invoking
 * the program with the following argument:
 * 
 * <pre>
 * -Dscorebored.talker=us.praefectus.scorebored.talker.MuteTalker
 * </pre>
 */
public class MuteTalker extends AbstractTalker {

    /**
     * The default and only voice: Mute.
     */
    public static final Voice DEFAULT_VOICE = new Voice("Mute");
    
    /**
     * The supported set of voices: only the Mute voice.
     */
    private static final Voice[] VOICES = { DEFAULT_VOICE };
    
    /**
     * Supported voices represented by a set.
     */
    private static final Set<Voice> VOICE_SET = Collections.unmodifiableSet(
            new TreeSet<Voice>(Arrays.asList(VOICES)));
    
    /**
     * Creates a new talker that does not talk.
     */
    public MuteTalker() {
        super(DEFAULT_VOICE);
    }
    
    @Override
    public void say(String sentence) throws TalkException, InterruptedException {
        muteSleep();
    }

    @Override
    public Set<Voice> getVoices() {
        return VOICE_SET;
    }
    
    /**
     * Always returns true.
     * 
     * @return true
     */
    @Override
    public boolean isMute() {
        return true;
    }

    /**
     * Does nothing.
     */
    @Override
    public void toggleMute() {
    }
    
}
