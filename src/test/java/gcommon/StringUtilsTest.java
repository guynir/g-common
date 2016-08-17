package gcommon;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test suite for {@link StringUtils}.
 *
 * @author Guy Raz Nir
 * @since 17/08/2016
 */
public class StringUtilsTest {

    @Test
    public void testEmailAddressValidator() {
        Assert.assertTrue(StringUtils.isValidEmail("guynir75@gmail.com"));
        Assert.assertFalse(StringUtils.isValidEmail("guynir75gmail.com"));
        Assert.assertFalse(StringUtils.isValidEmail("guynir75@gmail"));

    }

}
