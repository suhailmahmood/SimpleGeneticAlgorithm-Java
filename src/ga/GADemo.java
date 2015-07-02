package ga;

/**
 *
 * @author Suhail
 */
public class GADemo {

    public static void main(String[] args) {
        GenePool gp = new GenePool(20, 30, 0.75, 0.01);

        gp.evolve(10);

    }
}
