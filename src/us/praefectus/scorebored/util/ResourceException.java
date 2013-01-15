package us.praefectus.scorebored.util;

/**
 * Thrown when a resource could not be loaded from a JAR file.
 */
public class ResourceException extends Exception {
    
    private ResourceException() {
    }
    
    /**
     * Creates an exception with a message.
     * 
     * @param message the reason why the resource could not be loaded. 
     */
    public ResourceException(String message) {
        super(message);
    }
    
    /**
     * Creates an exception with a message and the exception that caused
     * the error.
     * 
     * @param message a description of the attempted resource to load.
     * @param throwable the exception that caused the error.
     */
    public ResourceException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
