package gcommon.objects;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test cases for {@link NumberFormatter}.
 *
 * @author Guy Raz Nir
 * @since 25/06/2016
 */
public class NumberFormatterTest {

    @Test
    public void testDefaultFormatting() {
        NumberFormatter formatter = new NumberFormatter();
        String result = formatter.format(123456789L);
        Assert.assertEquals("120,563.27", result);
    }

    @Test
    public void testFormattingWithDecimalKilo() {
        NumberFormatter formatter = new NumberFormatter().withDecimalKilo();
        String result = formatter.format(123456789L);
        Assert.assertEquals("123,456.78", result);
    }

    @Test
    public void testFormattingWithoutDecimalPadding() {
        NumberFormatter formatter = new NumberFormatter().withDecimalKilo().withoutDecimalPadding().withMaxDecimalDigits(6);
        String result = formatter.format(123456789L);
        Assert.assertEquals("123,456.789", result);
    }


    @Test
    public void testFormattingWithDecimalPadding() {
        NumberFormatter formatter = new NumberFormatter().withDecimalKilo().withDecimalPadding().withMaxDecimalDigits(6);
        String result = formatter.format(123456789L);
        Assert.assertEquals("123,456.789000", result);
    }


}
