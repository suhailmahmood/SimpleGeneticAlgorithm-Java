package genetics;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Suhail
 */
public class Chromosome {

    private ArrayList<Gene> genes;
    private int chromosomeLength = 16;

    /**
     * Create a chromosome containing 'length' number of genes, randomly
     * populated. If -1 is passed as 'length' argument, 16 is used which is the
     * default chromosome length
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

    public Chromosome(List<Gene> genes) {
        if (genes != null) {
            this.genes = new ArrayList(genes);
            this.chromosomeLength = genes.size();
        }
    }

    public void setChromosome(List<Gene> genes) {
        if (genes != null) {
            if (genes.size() == chromosomeLength) {
                this.genes = new ArrayList(genes);
            }
            else {
                throw new IllegalArgumentException("length of 'genes' must equal that of target Chromosome");
            }
        }
    }

    public List<Gene> getGenes(int fromIndex, int toIndex) {
        return new ArrayList<>(genes.subList(fromIndex, toIndex));
    }

    /**
     * Set chromosome length. For new chromosome length, the following checks
     * are performed:
     * <p>
     * - <tt>this.chromosomeLength > chromosomeLength</tt>: discard superfluous
     * genes at the back
     * </p>
     * <p>
     * - <tt>chromosomeLength > this.chromosomeLength</tt>: add
     * (this.chromosomeLength - chromosomeLength) number of genes, randomly
     * populated, at the back of the current chromosome
     * </p>
     *
     * @param chromosomeLength the length to set the current chromosome to.
     */
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
    
    public int getChromosomeLength(){
        return chromosomeLength;
    }

    public void setGene(int index, Gene gene) {
        genes.set(index, gene);
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
}
