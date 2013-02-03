package us.praefectus.scorebored.talker;

import us.praefectus.scorebored.util.Check;

/**
 * Implements the common operations of all Talkers.
 */
public abstract class AbstractTalker implements Talker {

    /**
     * The default time to wait when {@link Talker#say} is invoked while
     * muted: 2 seconds.
     */
    public static final long DEFAULT_MUTE_WAIT_TIME = 2000;
    
    /**
     * The style of voice to use.
     */
    private Voice voice;
    
    /**
     * Indicates if this talker is muted.
     */
    private boolean mute = false;
    
    /**
     * The time to wait, in milliseconds, if say is invoked while muted.
     */
    private long muteWaitTime = DEFAULT_MUTE_WAIT_TIME;
    
    /**
     * Creates a new talker.
     * 
     * @param defaultVoice the default voice style to use.
     */
    protected AbstractTalker(Voice defaultVoice) { 
        setVoice(Check.notNull(defaultVoice));
    }
    
    @Override
    public void say(String... sentences) throws TalkException, InterruptedException {
        for ( String sentence: sentences ) {
            say(sentence);
        }
    }
    
    @Override
    public void say(Commentary commentary) throws TalkException, InterruptedException {
        for ( Speech speech: commentary.getSentences() ) {
            say(speech.getSpeakAs());
        }
    }

    @Override
    public final void setVoice(Voice voice) {
        Check.notNull(voice);
        if ( !this.getVoices().contains(voice) ) { 
            throw new IllegalArgumentException("No such voice: " + voice);
        }
        this.voice = voice;
    }
    
    @Override
    public final Voice getVoice() {
        return this.voice;
    }
    
    @Override
    public boolean isMute() {
        return this.mute;
    }
    
    @Override
    public void setMute(boolean mute) { 
        this.mute = mute;
    }
    
    @Override
    public void toggleMute() {
        this.mute = ! this.mute;
    }
    
    @Override
    public long getMuteWaitTime() {
        return this.muteWaitTime;
    }
    
    @Override
    public void setMuteWaitTime(long waitTime) {
        this.muteWaitTime = Check.notNegative(waitTime);
    }
    
    /**
     * Sleeps for the specified time when muted. Returns immediately if the 
     * thread is interrupted.
     */
    protected void muteSleep() throws InterruptedException {
        Thread.sleep(muteWaitTime);
    }
}
