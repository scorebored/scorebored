package org.blackchip.scorebored.talker;

import java.util.List;
import java.util.Set;

/**
 * Interface for classes that support text-to-speech. 
 * 
 * <p>
 * Invoke the text-to-speech function by calling the {@link #say} method.
 * </p>
 * 
 * <p>
 * Talkers can support multiple styles of voice. Use {@link #getVoices} to 
 * find the styles supported by this class and set the style with 
 * {@link #setVoice}.
 * </p>
 * 
 * <p>
 * A talker can be muted by invoking {@link #setMute} or {@link #toggleMute}. 
 * While a muted talker won't say anything, it will block for a certain amount
 * of time in case subtitles are enabled. Change this blocking time with
 * {@link #setMuteWaitTime}.
 * </p>
 */
public interface Talker {

    /**
     * Speaks text.
     * 
     * <p>
     * If the voice has been muted, this method does nothing but waits for
     * the time specified by {@link #getMuteWaitTime()}. 
     * </p>
     * 
     * @param sentence the sentence to speak
     * @throws TalkException if an underlying error prevented the voice from
     * speaking.
     * @throws InterruptedException if the thread was interrupted
     */    
    public void say(String sentence) throws TalkException, InterruptedException;
    
    
    public void say(String... sentences) throws TalkException, InterruptedException;
    
    public void say(Commentary commentary) throws TalkException, InterruptedException;
    
    /**
     * Gets the set of voice styles supported by this talker.
     * 
     * @return the set of voices that can be used while speaking.
     */
    public Set<Voice> getVoices();
    
    /**
     * Gets the current voice style.
     * 
     * @return the style of voice that will be used when invoking {@link #say}.
     */
    public Voice getVoice();
    
    /**
     * Changes the voice style.
     * 
     * @param voice the style of voice to be used when invoking {@link #say}.
     * @throws IllegalArgumentException if the voice is not supported by this
     * talker.
     */
    public void setVoice(Voice voice);
    
    /**
     * Indicates if this talker has been muted.
     * 
     * @return true if calling {@link #say} will not produce any audible speech.
     */
    public boolean isMute();
    
    /**
     * Sets or clears the mute status of this talker.
     * 
     * @param mute true to mute speech, false to allow speech.
     */
    public void setMute(boolean mute);
    
    /**
     * Toggles the state of the mute status.
     */
    public void toggleMute();
    
    /**
     * Gets the time the talker should wait while muted.
     * 
     * @return the time in milliseconds.
     */
    public long getMuteWaitTime();
    
    /**
     * Sets the time the talker should wait while muted.
     * 
     * @param waitTime the time to wait in milliseconds.
     * @throws IllegalArgumentException if the wait time is negative.
     */
    public void setMuteWaitTime(long waitTime);
}
