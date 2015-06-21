package genetics;

/**
 * This is a single gene, which actually wraps a bit. Provides features to
 * manipulate the gene
 *
 * @author Suhail
 */
public class Gene {

    /**
     * the value of the gene, either 0 or 1
     */
    private int value;

    /**
     * Default constructor. Creates a gene with either a 0 or a 1, both having a
     * probability of 0.5
     */
    public Gene() {
        value = Math.random() <= 0.5 ? 0 : 1;
    }

    /**
     * Constructor with value of the gene as parameter. If the argument is
     * anything other than a 0 or a 1, IllegalArgumentException is thrown.
     *
     * @param value the value of the gene to set to
     */
    public Gene(short value) {
        if (value != 0 && value != 1) {
            throw new IllegalArgumentException("value must be either 0 or 1");
        }
        else {
            this.value = value;
        }
    }

    /**
     * Flips the current gene value. A 0 is flipped to a 1, and a 1 is flipped
     * to 0
     */
    public void mutate() {
        value = (short) (value == 1 ? 0 : 1);
    }

    /**
     * Get the current gene value, the bit.
     *
     * @return short - the current gene value, either 0 or 1.
     */
    public int value() {
        return value;
    }

    /**
     * Get the current gene value, as a String of 1 character, the bit.
     *
     * @return the current gene value, either 0 or 1, as a String.
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
