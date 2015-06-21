package genetics;

import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Suhail
 */
public class Chromosome {

    private ArrayList<Gene> genes;
    private int chromosomeLength = 16;

    /**
     * Create a chromosome containing 'length' number of genes, randomly
     * populated. If -1 is passed as 'lenght' argument, 16 is the default length
     *
     * @param length the length of chromosome to be created. To use default
     * value, make length zero or negative
     */
    public Chromosome(int length) {
        this.genes = new ArrayList<>();

        if (length > 0) {
            chromosomeLength = length;
        }
        for (int i = 0; i < chromosomeLength; i++) {
            this.genes.add(i, new Gene());
        }
    }

    /**
     * Create a Chromosome from an array of 'Gene's.
     *
     * @param genes the array of 'Gene's
     */
    public Chromosome(Gene[] genes) {
        if (genes != null) {
            this.genes = new ArrayList(Arrays.asList(genes));
        }
    }

    public void setChromosomeLength(int chromosomeLength) {
        if (chromosomeLength > 0) {
            this.chromosomeLength = chromosomeLength;
        }
        if (genes.size() > chromosomeLength) {
            genes.subList(chromosomeLength, genes.size()).clear();
        }
        else {
            for (int i = genes.size(); i < chromosomeLength; i++) {
                genes.add(new Gene());
            }
        }
    }

    /**
     * Re-populate the current chromosome's genes equal to those of
     * <code>genes</code> array.
     *
     * @param genes the array of 'Gene's to use to create the new Chromosome.
     */
    public void setChromosome(Gene[] genes) {
        if (genes != null) {
            this.genes = new ArrayList(Arrays.asList(genes));
        }
    }

    public Gene geneAt(int index) {
        if (index < 0 || index >= genes.size()) {
            throw new IndexOutOfBoundsException("\n\tAttempted index: " + index);
        }
        return genes.get(index);
    }

    /**
     * The Evaluation function for the Chromosome.
     *
     * @return The value of this Chromosome, according to the Evaluation
     * function.
     */
    public int value() {
        // evaluation function goes here
        return Integer.parseInt(this.toString(), 2);
    }

    @Override
    public String toString() {
        StringBuilder chromosome = new StringBuilder("");

        genes.stream().forEach((g) -> chromosome.append(g));

        return chromosome.toString();
    }
    
    public static void main(String[] args) {
        Chromosome c = new Chromosome(10);
        System.out.println(c.geneAt(11));
    }
}
