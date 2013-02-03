package us.praefectus.scorebored.talker;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;
import us.praefectus.scorebored.util.Strings;

/**
 * Talker on Linux platforms using the TODO: what package is this?.
 * 
 * <p>
 * This talker invokes the "espeak" command when the TODO: package? is 
 * installed.
 * </p>
 */
public class LinuxTalker extends AbstractTalker {

    /**
     * Path to the espeak executable.
     */
    public static final File executable = new File("/usr/bin/espeak");
    
    /**
     * Default voice style to use: American Male #1.
     */
    public static final Voice defaultVoice = 
            new Voice("en-us+m1", "American Male #1");
    
    /**
     * Supported voices by the "espeak" command.
     */    
    private static final Voice[] VOICES = {
        defaultVoice,  
        new Voice("en-us+m2", "American Male #2"), 
        new Voice("en-us+m3", "American Male #3"), 
        new Voice("en-us+m4", "American Male #4"), 
        new Voice("en-us+m5", "American Male #5"), 
        new Voice("en-us+m6", "American Male #6"), 
        new Voice("en-us+m7", "American Male #7"), 
        new Voice("en-us+f1", "American Female #1"), 
        new Voice("en-us+f2", "American Female #2"), 
        new Voice("en-us+f3", "American Female #3"),
        new Voice("en-us+f4", "American Female #4")
    };
    
    /**
     * Supported voices represented by a set.
     */
    private static final Set<Voice> VOICE_SET = Collections.unmodifiableSet(
            new TreeSet<Voice>(Arrays.asList(VOICES)));
    
    private Runtime runtime = Runtime.getRuntime();
    
    /**
     * Creates a new talker for the Linux platform.
     */
    public LinuxTalker() {
        super(defaultVoice);
    }
    
    @Override
    public Set<Voice> getVoices() {
        return VOICE_SET;
    }
      
    @Override
    public void say(String... sentences) 
            throws TalkException, InterruptedException {
        Process process = null;
        String chat = Strings.joinArray(" ", sentences);
        try {
            if ( !isMute() ) { 
                process = runtime.exec(new String[] {
                    "espeak", "-v" + getVoice().getId(), chat
                });
                process.waitFor();
                process = null;
            } else {
                muteSleep();
            }
        } catch ( IOException ie ) { 
            throw new TalkException(chat, ie);
        } finally {
            if ( process != null ) {
                process.destroy();
            }
        }
    }
    
    public static boolean isSupported() {
        return executable.canExecute();
    }
    
}
