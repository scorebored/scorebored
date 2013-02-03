package us.praefectus.scorebored.util;

/**
 * Input validation methods.
 * 
 * <p>
 * This class contains common methods for validating inputs from end users
 * or untrusted sources. Each method takes a failureMessage argument which is
 * the message to use in the exception if the validation fails.
 * </p>
 * 
 * @see ValidationException
 */
public final class Validate {
    
    private Validate() {
    }
    
    /**
     * Throws an exception if a value is negative.
     * 
     * @param <T> a Number
     * @param value the value to check.
     * @param failureMessage message to use in the exception if the validation
     * fails.
     * @return the value that was checked.
     * @throws ValidationException if the value is negative.
     */
    public static <T extends Number> T notNegative(T value, 
            String failureMessage) throws ValidationException { 
        if ( value.doubleValue() < 0 ) { 
            throw new ValidationException(failureMessage);
        }
        return value;
    }
    
    /**
     * Throws an exception if a string value is not a valid integer.
     * 
     * @param value string value to check.
     * @param failureMessage message to use in the exception if the validation
     * fails.
     * @return the value, converted to an integer.
     * @throws ValidationException if the value is not a valid integer. 
     */
    public static int isInteger(CharSequence value, String failureMessage) 
            throws ValidationException {
        try {
            int intValue = Integer.parseInt(value.toString());
            return intValue;
        } catch ( NumberFormatException nfe ) {
            throw new ValidationException(failureMessage);
        }
    }
    
    /**
     * Throws an exception if a string value is empty.
     * 
     * @param <T> a CharSequence
     * @param value the value to check.
     * @param failureMessage message to use in the exception if the validation
     * fails.
     * @return the value.
     * @throws ValidationException if {@link Strings#isEmpty} returns true.
     */
    public static <T extends CharSequence> T notEmpty(@Nullable T value,
            String failureMessage) throws ValidationException {
        if ( Strings.isEmpty(value) ) {
            throw new ValidationException(failureMessage);
        }
        return value;
    }

}
