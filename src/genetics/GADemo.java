/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package genetics;

/**
 *
 * @author Suhail
 */
public class GADemo {

    public static void main(String[] args) {
        GenePool gp = new GenePool(10, -1, 0.75, 0.05);
        System.out.printf("%s -> %s\n", gp.chromosomeAt(0), gp.chromosomeAt(0).value());
        System.out.printf("%s -> %s\n", gp.chromosomeAt(1), gp.chromosomeAt(1).value());
        Chromosome c = gp.crossOver(gp.chromosomeAt(0), gp.chromosomeAt(1));
        System.out.printf("%s -> %s\n", c, c.value());
    }
}