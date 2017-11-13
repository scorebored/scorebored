package org.blackchip.scorebored.util;

/**
 * Validation failure on an user supplied input value. The exception message
 * should be suitable to display to the end user.
 */
public class ValidationException extends Exception {

    private ValidationException() {
    }
    
    /**
     * Creates a new exception with a message.
     * 
     * @param message the user-friendly error message to display. 
     */
    public ValidationException(String message) { 
        super(message);
    }
}
