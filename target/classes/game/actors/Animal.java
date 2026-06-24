package game.actors;

/**
 * Represent the amount of the currency
 *
 * @author  Shee Seng Cheng
 * @version 1.0
 */
public enum Amount
{
    $0 (0),
    $1 (1),
    $2 (2),
    $3 (3),
    $4 (4),
    $5 (5);

    private final int VALUE;

    /**
     * Constructor for enum
     * @param value the value of this
     */
    Amount (int value){
        this.VALUE = value;
    }

    /**
     * Getter to get the value.
     * @return int the value of this
     */
    public int getVALUE()
    {
        return VALUE;
    }
}
