package gcommon;

/**
 * Enumeration of common size units.
 *
 * @author Guy Raz Nir
 * @since 25/06/2016
 */
public enum SizeUnits {

    BYTES(""),
    KB("K"),
    MB("M"),
    GB("G"),
    TB("T");

    /**
     * Symbol representing the unit.
     */
    public final String symbol;

    /**
     * Class constructor.
     *
     * @param symbol Symbol representing the unit.
     */
    SizeUnits(String symbol) {
        this.symbol = symbol;
    }

}
