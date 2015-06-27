package genetics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Suhail
 */
public class GenePool {

    private final List<Chromosome> genePool;
    private int chromosomeLength;
    private final double crossOverRate;
    private final double mutationRate;
    private int[] crossPoints;

    /**
     * Constructor to create a gene pool, specified by the parameters.
     *
     * @param numOfChromosome number of Chromosomes in the gene pool
     * @param chromosomeLength length of each Chromosome in the gene pool
     * @param crossOverRate the cross-over rate in the gene pool
     * @param mutationRate the mutation rate in the gene pool
     */
    public GenePool(int numOfChromosome, int chromosomeLength, double crossOverRate,
            double mutationRate) {

        this.chromosomeLength = chromosomeLength <= 0 ? 16 : chromosomeLength;
        this.crossOverRate = crossOverRate;
        this.mutationRate = mutationRate;

        crossPoints = new int[1];
        crossPoints[0] = this.chromosomeLength / 2;

        genePool = new ArrayList<>();
        for (int i = 0; i < numOfChromosome; i++) {
            genePool.add(new Chromosome(chromosomeLength));
        }
    }

    public Chromosome chromosomeAt(int index) {
        return genePool.get(index);
    }

    public void setChromosomeLength(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
    }

    /**
     * Get the current chromosome length.
     *
     * @return the current chromosome length.
     */
    public int getChromosomeLength() {
        return chromosomeLength;
    }

    public void setCrossPoints(int[] crossPoints) {
        if (crossPoints != null) {
            this.crossPoints = crossPoints;

            Arrays.sort(this.crossPoints);
            if (this.crossPoints[0] < 1 || this.crossPoints[crossPoints.length - 1] >= this.chromosomeLength - 1) {
                throw new IllegalArgumentException("values in the crossPoints array must be within 1 and chromosomeLength-2 inclusive");
            }
        }
    }

    public void displayChromosomes() {
        genePool.stream().forEach((c) -> {
            System.out.println(c + " -> " + String.valueOf(c.value()));
        });
    }

    public Chromosome crossOver(Chromosome c1, Chromosome c2) {
        ArrayList<Gene> geneList = new ArrayList<>();

        Chromosome[] parentChromosomes = new Chromosome[2];
        parentChromosomes[0] = c1;
        parentChromosomes[1] = c2;

        int i, start;
        for (i = start = 0; i < crossPoints.length; i++) {
            geneList.addAll(start, parentChromosomes[i % 2].getGenes(start, crossPoints[i]));
            start = crossPoints[i];
            
        }
        geneList.addAll(start, parentChromosomes[i % 2].getGenes(start, c1.getChromosomeLength()));
        return new Chromosome(geneList);
    }
}
