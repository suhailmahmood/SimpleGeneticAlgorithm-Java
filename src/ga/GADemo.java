/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ga;

/**
 *
 * @author Suhail
 */
public class GADemo {

    public static void printChromosome(Chromosome c) {
        int len = c.getChromosomeLength();
        StringBuilder chromosome = new StringBuilder("");
        for (int i = 0; i < len; i++) {
            if (i == len / 2) {
                chromosome.append("|");
            }
            chromosome.append(c.getGeneAt(i));
        }
        System.out.println(chromosome);
    }

    public static void main(String[] args) {
        GenePool gp = new GenePool(10, 16, 0.75, 0.05);
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s -> %s\n", gp.getChromosomeAt(i), gp.getChromosomeAt(i).value());
        }
        System.out.println("");
        
        gp.sort();

        for (int i = 0; i < 10; i++) {
            System.out.printf("%s -> %s\n", gp.getChromosomeAt(i), gp.getChromosomeAt(i).value());
        }
        System.out.println("");
//        System.out.printf("%s -> %s\n", gp.getChromosomeAt(0), gp.getChromosomeAt(0).value());
//        System.out.printf("%s -> %s\n", gp.getChromosomeAt(1), gp.getChromosomeAt(1).value());
//        Chromosome[] c = gp.crossOver(gp.getChromosomeAt(0), gp.getChromosomeAt(1));
//        System.out.printf("%s -> %s\n", c[0], c[0].value());
//        System.out.printf("%s -> %s\n", c[1], c[1].value());
    }
}
