package ga;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Suhail
 */
public class Chromosome implements Comparable {

    private ArrayList<Gene> genes;
    private final int chromosomeLength;

    public Chromosome(int length) {
        this.genes = new ArrayList<>();

        this.chromosomeLength = length > 0 ? length : 16;

        for (int i = 0; i < chromosomeLength; i++) {
            this.genes.add(i, new Gene());
        }
    }

    public List<Gene> getGenes() {
        return genes;
    }

    public void setGenes(ArrayList<Gene> genes) {
        this.genes = genes;
    }

    public List<Gene> getAllele(int fromIndex, int toIndex) {
        return new ArrayList<>(genes.subList(fromIndex, toIndex));
    }

    public void setAllele(int fromIndex, List<Gene> allele) {

        int lastIndex = fromIndex + allele.size();
        if (lastIndex > chromosomeLength) {
            throw new IndexOutOfBoundsException("the allele exceeds beyond the size of the chromosome");
        }
        for (int i = fromIndex, j = 0; i < lastIndex; i++, j++) {
            genes.set(i, allele.get(j));
        }
    }

    public int getChromosomeLength() {
        return chromosomeLength;
    }

    public void setGeneAt(int index, Gene gene) {
        genes.set(index, gene);
    }

    public Gene getGeneAt(int index) {
        return genes.get(index);
    }

    public int value() {
        // evaluation function goes here
        return Integer.parseInt(this.toString(), 2);
    }

    @Override
    public String toString() {
        StringBuilder chromosome = new StringBuilder("");

        genes.stream().forEach((Gene g) -> chromosome.append(g));

        return chromosome.toString();
    }

    /**
     * Compares this Chromosome with the specified object(a Chromosome) for
     * order. Returns a negative integer, zero, or a positive integer as the
     * value of this Chromosome is less than, equal to, or greater than the
     * specified chromosome. When any Collection of Chromosome objects is
     * sorted, it is sorted in <strong>ascending order</strong>. To sort in descending order, the
     * return statement is to be reversed.
     *
     * @param anotherChromosome the object(Chromosome) to be compared.
     * @return a negative integer, zero, or a positive integer as the value of
     * this chromosome is less than, equal to, or greater than the specified
     * chromosome.
     */
    @Override
    public int compareTo(Object anotherChromosome) {
        Chromosome c = (Chromosome) anotherChromosome;
        return this.value() - c.value();
    }
}
