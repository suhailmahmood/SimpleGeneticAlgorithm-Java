package ga;

/**
 *
 * @author Suhail
 */
public class GADemo {

    public static void main(String[] args) {
        GenePool gp = new GenePool(2, 8, 0.75, 0.05);

        Chromosome[] c = gp.crossOver(gp.getChromosomeAt(0), gp.getChromosomeAt(1));
        System.out.println(gp.getChromosomeAt(0) + " -> " + gp.getChromosomeAt(0).value());
        System.out.println(gp.getChromosomeAt(1) + " -> " + gp.getChromosomeAt(1).value());
        System.out.println(c[0] + " -> " + c[0].value());
        System.out.println(c[1] + " -> " + c[1].value());

    }
}
