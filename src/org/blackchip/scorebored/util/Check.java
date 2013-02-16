package org.blackchip.scorebored.util;

/**
 * Argument checking.
 * 
 * <p>
 * Provides methods for common checks on method arguments. All methods throw
 * a {@link RuntimeException} if the check fails.
 * </p>
 */
public final class Check {
    
    private Check() {
    }
    
    /**
     * Checks that a value is not null. 
     * 
     * <p>This is useful for preventing delayed NullPointerExceptions where an 
     * object is not used immediately but stored for later use. Example:
     * 
     * <pre>
     * public class Foo { 
     * 
     *     private Bar bar;
     * 
     *     public Foo(Bar bar) {
     *         this.bar = Check.notNull(bar);
     *     }
     * }
     * </pre>
     * 
     * @param <T> any type.
     * @param value the value to check
     * @return the value
     * @throws NullPointerException if the value is null.
     */
    public static <T> T notNull(T value) {
        if ( value == null ) {
            throw new NullPointerException();
        }
        return value;
    }
    
    /**
     * Checks that a string value is not empty.
     * 
     * @param <T> of CharSequence
     * @param value the value to check
     * @return the value
     * @throws NullPointerException if the value is null.
     * @throws IllegalArgumentException if the trimmed length of the string
     * is zero.
     */
    public static <T extends CharSequence> T notEmpty(T value) {
        if ( Check.notNull(value).toString().trim().isEmpty() ) {
            throw new IllegalArgumentException("Is empty");
        }
        return value;
    }
    
    /**
     * Checks that a numeric value is not negative.
     * 
     * @param <T> of Number
     * @param value the value to check
     * @return the value
     * @throws IllegalArgumentException if the value is negative.
     */
    public static <T extends Number> T notNegative(T value) { 
        if ( value.doubleValue() < 0 ) { 
            throw new IllegalArgumentException("Is negative: " + value);
        }
        return value;
    }
}
