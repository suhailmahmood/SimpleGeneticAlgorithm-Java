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

    @Override
    public int compareTo(Object o) {
        Chromosome c = (Chromosome) o;
        return value() - c.value();
    }
}
