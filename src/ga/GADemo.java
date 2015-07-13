package ga;

/**
 *
 * @author Suhail
 */
public class GADemo {

    public static void main(String[] args) {
        GenePool gp = new GenePool(10, 0, 0.25, 0.01);

        gp.evolve(100);
    }
}
