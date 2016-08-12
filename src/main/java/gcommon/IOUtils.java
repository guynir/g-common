package gcommon;

import java.io.Closeable;

/**
 * I/O related utilities.
 *
 * @author Guy Raz Nir
 * @since 17/07/2017
 */
public class IOUtils {

    /**
     * Close a handle forcibly, suppressing any error.
     *
     * @param closeable Closable to close. If {@code null}, no action is performed.
     */
    public static void forceClose(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception ex) {
                // Suppress error.
            }
        }
    }
}
