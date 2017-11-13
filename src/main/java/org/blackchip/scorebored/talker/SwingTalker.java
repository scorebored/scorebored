package org.blackchip.scorebored.talker;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import javax.swing.SwingWorker;
import org.apache.log4j.Logger;
import org.blackchip.scorebored.swing.Swing;
import org.blackchip.scorebored.util.Check;
import org.blackchip.scorebored.util.Strings;

/**
 * Talker that can be used in a Swing environment.
 * 
 * <p>
 * This class will use a {@link SwingWorker} to execute the talk process which
 * has the following features:
 * 
 * <ul>
 * <li>The talk process will be executed in another Thread. This allows the 
 * GUI to remain responsive while the process is executing.
 * <li>Cancellation support. If a talk request is submitted while a 
 * previous request is still talking, the previous request will be terminated
 * immediately and the new request will be executed.
 * </ul>
 * 
 * <p>
 * Listeners can be registered to notify when a talk request starts and
 * when the talking stops. 
 * </p>
 */
public class SwingTalker implements Talker {
    
    private static final Logger log = Logger.getLogger(SwingTalker.class);
    
    /**
     * The talker instance to use.
     */
    private Talker talker;
    
    /**
     * The SwingWorker instance that does the work.
     */
    private Worker worker = null;
    
    /**
     * Listeners for talk events.
     */
    private List<TalkListener> listeners = new LinkedList<TalkListener>();
        
    private SwingTalker() {
    }
    
    /**
     * Creates a new SwingTalker using the specified Talker.
     * 
     * @param talker the talker instance to use.
     */
    public SwingTalker(Talker talker) { 
        this.talker = Check.notNull(talker);
    }
            
    @Override
    public Set<Voice> getVoices() {
        return talker.getVoices();
    }
    
    @Override
    public Voice getVoice() {
        return talker.getVoice();
    }
    
    @Override
    public void setVoice(Voice voice) {
        talker.setVoice(voice);
    }
    
    @Override
    public boolean isMute() {
        return talker.isMute();
    }
    
    @Override
    public void toggleMute() {
        talker.toggleMute();
    }
    
    public void silence() {
        if ( worker != null && !worker.isDone() ) {
            log.debug("Silence");
            worker.cancel(true);
        }        
    }
    
    @Override
    public void say(Commentary commentary) {
        silence();
        worker = new Worker(commentary);
        worker.execute();
    }
    
    @Override
    public void say(String... sentences) {
        say(Commentary.fromArray(sentences));
    }
    
    @Override
    public void say(String sentence) {
        Commentary commentary = new Commentary();
        commentary.add(sentence);
        say(commentary);
    }
    
    public void addListener(TalkListener listener) { 
        listeners.add(Check.notNull(listener));
    }
    
    public boolean removeListener(TalkListener listener) { 
        return listeners.remove(Check.notNull(listener));
    }

    @Override
    public void setMute(boolean mute) {
        talker.setMute(mute);
    }

    @Override
    public long getMuteWaitTime() {
        return talker.getMuteWaitTime();
    }

    @Override
    public void setMuteWaitTime(long waitTime) {
        talker.setMuteWaitTime(waitTime);
    }
    
    class Worker extends SwingWorker<Object,Speech> {

        private Commentary commentary;
        private Exception exception = null;

        public Worker(Commentary commentary) { 
            this.commentary = Check.notNull(commentary);
        }

        public boolean failed() {
            return this.exception != null;
        }

        public Exception getException() {
            return this.exception;
        }

        @Override
        protected Object doInBackground() throws Exception {
            try {
                for ( Speech sentence: commentary.getSentences() ) {
                    publish(sentence);
                    talker.say(sentence.getSpeakAs());
                    if ( isCancelled() ) {
                        return null;
                    }
                }
            } catch ( Exception e ) {
                this.exception = e;
            }
            return null;
        }
        
        @Override
        protected void process(List<Speech> chunks) {
            for ( Speech chunk: chunks ) { 
                log.debug("Commentator: " + chunk.getDisplayAs());
                for ( TalkListener listener: listeners ) {
                    listener.talkStarted(chunk);
                }
            }   
        }
        
        @Override
        protected void done() {
            if ( failed() ) { 
                Swing.showError(this.exception);
            }
            for ( TalkListener listener: listeners ) { 
                listener.talkEnded();
            }
        }
    }    
}

