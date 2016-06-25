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
    public String symbol;

    /**
     * Class constructor.
     *
     * @param symbol Synbol representign the unit.
     */
    SizeUnits(String symbol) {
        this.symbol = symbol;
    }

}
