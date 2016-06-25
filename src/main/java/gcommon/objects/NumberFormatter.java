package gcommon.objects;

import gcommon.SizeUnits;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * Format a numeric value to convenient human readable format.
 *
 * @author Guy Raz Nir
 * @since 25/06/2016
 */
public class NumberFormatter {

    /**
     * Maximum number of digits to allow. When formatting a number that overflow beyond this limit, the value is
     * automatically divided by 1K and an upper level units are assigned.
     */
    private int maxDigits = 6;

    /**
     * Maximum number of decimal digits to allow.
     */
    private int maxDecimalDigits = 2;

    /**
     * {@code true} if decimal digits should have padding with zeros to reach value specified by
     * {@link #maxDecimalDigits} (e.g.: 23.8400), {@code false} if not (e.g.: 23.84).
     */
    private boolean decimalPadding = false;

    /**
     * Value of a single kilo.
     */
    private int kilo = 1024;

    /**
     * Formatter.
     */
    private DecimalFormat formatter = new DecimalFormat();

    public NumberFormatter() {
        formatter = newDecimalFormat();
    }

    /**
     * Sets the maximum allowed digits. If given value has more digits than this value, the value is converted to higher
     * unit (e.g.: for maxDigits = 4 and value of 634123, the value is converted to 634.123K). If this value is equal
     * or less than 1, the value will never be converted (e.g.: if maxDigits=1 and value of 645123 the formatter will
     * generate value of 645,123).<p>
     * <b>Default value is 6.</b>
     *
     * @param maxDigits Maximum allowed digits.
     * @return This instance.
     */
    public NumberFormatter withMaxDigits(int maxDigits) {
        this.maxDigits = maxDigits;
        this.formatter = newDecimalFormat();
        return this;
    }

    /**
     * Number of decimal digits to allow on output. For example, when converting 634123 with
     * {@code maxDecimalDigits} = 1 (and {@link #withMaxDigits(int) maxDigits} = 3), the generated value would be
     * <i>634.1K</i>.
     * <p>
     * <b>Default value is 2.</b>
     *
     * @param maxDecimalDigits Maximum digits allowed
     * @return This instance.
     */
    public NumberFormatter withMaxDecimalDigits(int maxDecimalDigits) {
        this.maxDecimalDigits = maxDecimalDigits;
        this.formatter = newDecimalFormat();
        return this;
    }

    /**
     * Specify to always pad with zeros decimal value to reach value specified by
     * {@link #withMaxDecimalDigits(int)} maxDecimalDigits}.
     *
     * @return This instance.
     */
    public NumberFormatter withDecimalPadding() {
        this.decimalPadding = true;
        this.formatter = newDecimalFormat();
        return this;
    }

    /**
     * Specify to never pad with zeros decimal values (e.g.: value 23.8400 will be truncated to 23.84).<p>
     * <b>This is the default behavior.</b>
     *
     * @return This instance.
     */
    public NumberFormatter withoutDecimalPadding() {
        this.decimalPadding = false;
        this.formatter = newDecimalFormat();
        return this;
    }

    /**
     * Specify that 1K is 1,000 units (e.g.: when formatting 10240, generated value will 10.24K).
     *
     * @return This instance.
     */
    public NumberFormatter withDecimalKilo() {
        this.kilo = 1000;
        return this;
    }

    /**
     * Specify that 1K is 1,024 units (e.g.: when formatting 10240, generated value will 10K).
     * <b>This is the default behavior.</b>
     *
     * @return This instance.
     */
    public NumberFormatter withBinaryKilo() {
        this.kilo = 1024;
        return this;
    }

    /**
     * Format a given value.
     *
     * @param value Value to format.
     * @return Formatted value.
     */
    public String format(long value) {
        return format(BigDecimal.valueOf(value));
    }

    /**
     * Format a given value.
     *
     * @param value Value to format.
     * @return Formatted value.
     */
    public String format(BigDecimal value) {
        int unitsIndex = 0;
        if (maxDigits > 1) {
            value = value.setScale(maxDecimalDigits, BigDecimal.ROUND_DOWN);
            BigDecimal referenceValue = BigDecimal.TEN.pow(maxDigits);
            BigDecimal divider = BigDecimal.valueOf(kilo);
            while (unitsIndex < SizeUnits.values().length && value.compareTo(referenceValue) >= 0) {
                value = value.divide(divider, BigDecimal.ROUND_DOWN);
                unitsIndex++;
            }

        }

        return formatter.format(value) + SizeUnits.values()[unitsIndex].symbol;
    }

    /**
     * @return A new {@code DecimalFormat} configured based of this formatter's properties.
     */
    private DecimalFormat newDecimalFormat() {
        // Basic format.
        StringBuilder buf = new StringBuilder(32).append("#,##0");

        // If decimal digits required -
        if (maxDecimalDigits > 0) {
            buf.append('.');
            // If padding is required for decimal places, use '0', otherwise use '#'.
            char decimalPatternSymbol = this.decimalPadding ? '0' : '#';
            for (int count = 0; count < maxDecimalDigits; count++) {
                buf.append(decimalPatternSymbol);
            }
        }

        return new DecimalFormat(buf.toString());
    }

}
