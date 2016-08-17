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

    /**
     * Test cases for {@link StringUtils#isValidEmail(String)}
     */
    @Test
    public void testEmailAddressValidator() {
        Assert.assertTrue(StringUtils.isValidEmail("guynir75@gmail.com"));
        Assert.assertFalse(StringUtils.isValidEmail("guynir75gmail.com"));
        Assert.assertFalse(StringUtils.isValidEmail("guynir75@gmail"));
    }

    /**
     * Test conversion byte-array to hexadecimal-representation string (see {@link StringUtils#toString(byte[])}).
     */
    @Test
    public void testByteArrayToStringConversion() {
        Assert.assertEquals("102030A000", StringUtils.toString(new byte[]{0x10, 0x20, 0x30, (byte) 0xA0, 0x00}).toUpperCase());
        Assert.assertNull(StringUtils.toString(null));
        Assert.assertEquals(0, StringUtils.toString(new byte[0]).length());
    }

}
