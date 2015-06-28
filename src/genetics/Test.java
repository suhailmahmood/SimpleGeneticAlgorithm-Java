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
public class Test {

    public static void main(String[] args) {
        
        GenePool gp = new GenePool(5, 6, 0.75, 0.05);
        System.out.println(gp.chromosomeAt(0));
        System.out.println(gp.chromosomeAt(1));
        int cp[] = {2};
        gp.setCrossPoints(cp);
        Chromosome offspring = gp.crossOver(gp.chromosomeAt(0), gp.chromosomeAt(1));
        System.out.println(offspring);
    }

}
