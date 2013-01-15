package us.praefectus.scorebored.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * IO utilities.
 */
public final class IO {
 
    private IO() {
    }
    
    /**
     * Attempts to close a stream. This should be used in finally block as 
     * in the following example:
     * 
     * <pre>
     * InputStream is = null;
     * try {
     *     is = new FileInputStream(...);
     *     ... do work ...
     *     is.close();
     * } finally {
     *     IO.release(is);
     * }
     * </pre>
     * 
     * @param closeable the object to attempt to close. If the value is null, 
     * this method does nothing, otherwise it invokes the close method and
     * discards any exceptions.
     */
    public static void release(Closeable closeable) { 
        if ( closeable != null ) { 
            try { 
                closeable.close();
            } catch ( IOException ie ) {
                // Nothing
            }
        }
    }
}
