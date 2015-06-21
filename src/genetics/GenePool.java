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
    private double crossOverRate;
    private double mutationRate;
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

        this.chromosomeLength = chromosomeLength;
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

    public void setCrossOverRate(float crossOverRate) {
        this.crossOverRate = crossOverRate;
    }

    public void setMutationRate(float mutationRate) {
        this.mutationRate = mutationRate;
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

    public static void main(String[] args) {
        GenePool gp = new GenePool(10, -1, 0.75, 0.05);
        gp.displayChromosomes();

    }
}
