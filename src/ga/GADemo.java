package ga;

/**
 *
 * @author Suhail
 */
public class GADemo {

    public static void display(GenePool gp) {
        System.out.println("GenePool:");
        for (int i = 0; i < gp.getGenePoolSize(); i++) {
            System.out.println(gp.getChromosomeAt(i).value());
        }
    }
    public static void main(String[] args) {
        GenePool gp = new GenePool(6, 8, 0.75, 0.01);
        display(gp);
        
        
// for testing saveFittest method in GenePool class
//        System.out.println("");
//        ArrayList<Chromosome> offsprings = new ArrayList<>();
//        for(int i=0; i<3; i++) {
//            offsprings.add(new Chromosome(8));
//            System.out.println(offsprings.get(i).value());
//        }
//        System.out.println("");
//        
//        
//        ArrayList<Chromosome> all = new ArrayList<>();
//        for (int i = 0; i < gp.getGenePoolSize(); i++) {
//            all.add(gp.getChromosomeAt(i));
//        }
//        offsprings.stream().forEach((offspring) -> {
//            all.add(offspring);
//        });
//        
//        gp.saveFittest(offsprings);
//        display(gp);
//        
//        System.out.println("-----------------");
//        
//        all.sort(null);
//        
//        all.stream().forEach((c) -> {
//            System.out.println(c.value());
//        });
        
    }
}
