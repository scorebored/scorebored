package us.praefectus.scorebored.talker;

/**
 * Listener for talk events.
 */
public interface TalkListener {

    /**
     * Invoked when a phrase to be spoken has started.
     * 
     * @param text the text that will be spoken.
     */
    public void talkStarted(String text);
    
    /**
     * Invoked when the previous text has finished being spoken,
     */
    public void talkEnded();
    
}
