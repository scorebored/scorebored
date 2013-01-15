package us.praefectus.scorebored.talker;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import us.praefectus.scorebored.util.Strings;

/**
 * Talker on Mac OS X platforms.
 * 
 * <p>
 * This talker invokes the "say" command that is distributed with the 
 * operating system.
 * </p>
 */
public class MacTalker extends AbstractTalker {

    /**
     * Path to the say executable.
     */
    public static final File executable = new File("/usr/bin/say");
    
    /**
     * Default voice style to use: Alex.
     */
    public static final Voice defaultVoice = new Voice("Alex");
    
    /**
     * Supported voices by the "say" command.
     */
    private static final Voice[] voices = {
        defaultVoice, 
        new Voice("Bruce"), 
        new Voice("Fred"), 
        new Voice("Junior"), 
        new Voice("Ralph"),
        new Voice("Agnes"), 
        new Voice("Kathy"), 
        new Voice("Princess"), 
        new Voice("Vicki"), 
        new Voice("Victoria"),
        new Voice("Albert"), 
        new Voice("Bad News"), 
        new Voice("Bahh"), 
        new Voice("Bells"), 
        new Voice("Boing"), 
        new Voice("Bubbles"), 
        new Voice("Cellos"), 
        new Voice("Deranged"), 
        new Voice("Good News"), 
        new Voice("Hysterical"), 
        new Voice("Pipe Organ"), 
        new Voice("Trinoids"), 
        new Voice("Whisper"), 
        new Voice("Zarvox")
    };
    
    /**
     * Supported voices represented by a set.
     */
    private static final Set<Voice> voiceSet = Collections.unmodifiableSet(
            new TreeSet<Voice>(Arrays.asList(voices)));
    
    private Runtime runtime = Runtime.getRuntime();
    
    /**
     * Creates a new Mac OS X Talker.
     */
    public MacTalker() {
        super(defaultVoice);
    }
    
    @Override
    public Set<Voice> getVoices() {
        return voiceSet;
    }
      
    @Override
    public void say(String... sentences) 
            throws TalkException, InterruptedException {
        Process process = null;
        String chat = Strings.joinArray(" ", sentences);
        try {
            if ( !isMute() ) {
                process = runtime.exec(new String[] {
                    "say", "--voice=" + this.getVoice().getId(), chat
                });
                process.waitFor();
                process = null;
            } else {
                muteSleep();
            }
        } catch ( IOException ie ) { 
            throw new TalkException(chat, ie);
        } catch ( InterruptedException ie ) {
            if ( process != null ) {
                process.destroy();
            }
        } 
    }
    
    public static boolean isSupported() {
        return executable.canExecute();
    }
}
