package gcommon;

import org.springframework.util.Assert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A collection of validation related utilities.
 *
 * @author Guy Nir
 * @since 24/11/2011
 */
public class ValidationUtil {

    /**
     * RFC-822 regular expression that specify E-mail address.
     */
    private static final String EMAIL_PATTERN_EXPRESSION = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";

    /**
     * A regular expression pattern matcher.
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_PATTERN_EXPRESSION);

    /** Maximum length of an E-mail address. */
    private static final int EMAIL_MAX_LENGTH = 256;

    /**
     * Validate that a given E-mail address string has valid format. This method does not actually validate that the
     * given <i>email</i> actually exist.
     *
     * @param email E-mail string to validate format.
     * @return {@code true} if E-mail has validate format, {@code false} otherwise.
     * @throws IllegalArgumentException If <i>email</i> is {@code null}.
     */
    public static boolean isValidEmail(String email) throws IllegalArgumentException {
        Assert.notNull(email, "E-mail cannot be null.");

        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return email.length() <= EMAIL_MAX_LENGTH && matcher.matches();
    }
}
