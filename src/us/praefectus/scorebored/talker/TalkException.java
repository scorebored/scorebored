package us.praefectus.scorebored.talker;

/**
 * Thrown when a Talker was unable to speak.
 */
public class TalkException extends Exception {
    
    private TalkException() {
    }

    /**
     * Creates a new exception with a message that the exception that 
     * caused the error.
     * 
     * @param message a descriptive message about this error.
     * @param throwable the exception that caused the error.
     */
    public TalkException(String message, Throwable throwable) { 
        super(message, throwable);
    }
    
}
