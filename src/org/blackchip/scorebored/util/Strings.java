package org.blackchip.scorebored.util;

import java.text.BreakIterator;
import java.util.LinkedList;
import java.util.List;

/**
 * String utility methods.
 */
public class Strings {

    private Strings() {
    }
    
    /**
     * Determines if a string value is empty.
     * 
     * @param value the value to check.
     * @return true if the value is null or if the trimmed length of the
     * string is zero.
     */
    public static boolean isEmpty(@Nullable CharSequence value) { 
        return value == null || value.toString().trim().isEmpty();
    }
    
    /**
     * Combines and delimits multiple values into a single string. Example:
     * 
     * <pre>
     * Strings.join(", ", new Object[] { "One", "Two", "Three" })
     * </pre>
     * 
     * Returns the string of:
     * 
     * <pre>
     * "One, Two, Three"
     * </pre>
     * 
     * @param delimiter the string used to delimit each value.
     * @param values the value to be combined using the String.valueOf method.
     * @return the combined string.
     */
    public static String joinArray(String delimiter, Object[] values) {
        Check.notNull(delimiter);
        StringBuilder sb = new StringBuilder();
        for ( Object value: values ) {
            if ( sb.length() != 0 ) {
                sb.append(delimiter);
            }
            sb.append(String.valueOf(value));
        }
        return sb.toString();        
    }
    
    /**
     * Combines and delimits multiple values into a single string. Example:
     * 
     * <pre>
     * Strings.join(", ", "One", "Two", "Three")
     * </pre>
     * 
     * Returns the string of:
     * 
     * <pre>
     * "One, Two, Three"
     * </pre>
     * 
     * @param delimiter the string used to delimit each value.
     * @param values the value to be combined using the String.valueOf method.
     * @return the combined string.
     */
    public static String join(String delimiter, @Nullable Object... values) { 
        return joinArray(delimiter, values);
    }


    public static String wrapText(int maxCharacters, String text) {
        text = String.valueOf(text);
        List<String> lines = new LinkedList<String>();
        BreakIterator iter = BreakIterator.getLineInstance();
        iter.setText(text);
        StringBuilder currentLine = new StringBuilder();
        int start = iter.first();
        
        // Looks confusing, but that is how they do it in the javadoc
        for ( int end = iter.next(); end != BreakIterator.DONE; 
                start = end, end = iter.next() ) {
            if ( currentLine.length() + (end - start) > maxCharacters ) {
                lines.add(currentLine.toString().trim());
                currentLine = new StringBuilder();
            }
            currentLine.append(text.subSequence(start, end));
        }
        lines.add(currentLine.toString().trim());
        return joinArray("\n", lines.toArray(new String[] {}));
    }
}
