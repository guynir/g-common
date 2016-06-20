package gcommon;

import org.springframework.util.Assert;

import java.util.Formatter;

/**
 * A collection of general service related to strings.
 *
 * @author Guy Nir
 * @since 29/11/2011
 */
public class StringUtils {

    /**
     * Build a string based on a given <i>format</i> and a collection of <i>arguments</i>. The specific
     * format can be reviewed in {@link java.util.Formatter} documentation.
     *
     * @param format    Format of string (e.g.: 'Hello there Mr. %s').
     * @param arguments Variable argument with values.
     * @return The string generated from <i>format</i> and <i>arguments</i>.
     * @throws IllegalArgumentException If <i>format</i> is {@code null}.
     * @see Formatter
     */
    public static String formats(String format, Object... arguments) throws IllegalArgumentException {
        Assert.notNull(format, "Format cannot be null.");
        return formats(null, format, arguments).toString();
    }

    /**
     * Build a string based on a given <i>format</i> and a collection of <i>arguments</i>. The specific
     * format can be reviewed in {@link java.util.Formatter} documentation.
     *
     * @param targetBuf Target buffer to place generated message. If this value is {@code null}, a new buffer
     *                  is created.
     * @param format    Format of string (e.g.: 'Hello there Mr. %s').
     * @param arguments Variable argument with values.
     * @return The {@code StringBuilder} where message is placed (either the one supplied by the caller or ane instance
     *         if caller passed {@code null} for the <i>targetBuf</i> arguments).
     * @throws IllegalArgumentException If <i>format</i> is {@code null}.
     * @see Formatter
     */
    public static StringBuilder formats(StringBuilder targetBuf, String format, Object... arguments) throws IllegalArgumentException {
        Assert.notNull(format, "Format cannot be null.");
        if (targetBuf == null) {
            targetBuf = new StringBuilder(format.length());
        }

        Formatter formatter = new Formatter(targetBuf);
        formatter.format(format, arguments);

        return targetBuf;
    }

    /**
     * Check if a given string is empty or not. An empty string is one that is either {@code null}, has no characters or
     * filled with spaces.
     *
     * @param string String to check.
     * @return {@code true} is string is empty, {@code false} otherwise.
     */
    public static boolean isEmpty(String string) {
        return string == null || string.length() == 0 || string.trim().length() == 0;
    }

}
