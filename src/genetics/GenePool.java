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
     * Constructor to create a gene pool, specified by the parameters. Default
     * is a single cross-point set at half the chromosome length.
     *
     * @param numOfChromosome number of Chromosomes in the gene pool
     * @param chromosomeLength length of each Chromosome in the gene pool
     * @param crossOverRate the cross-over rate in the gene pool
     * @param mutationRate the mutation rate in the gene pool
     */
    public GenePool(int numOfChromosome, int chromosomeLength, double crossOverRate, double mutationRate) {

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

    /**
     *
     * @param index
     * @return
     */
    public Chromosome getChromosomeAt(int index) {
        return genePool.get(index);
    }

    /**
     *
     * @param index
     * @param c
     */
    public void setChromosomeAt(int index, Chromosome c) {
        genePool.set(index, c);
    }

    /**
     * Get the chromosome length of the chromosomes in the gene pool.
     *
     * @return the current chromosome length.
     */
    public int getChromosomeLength() {
        return chromosomeLength;
    }

    /**
     *
     * @param chromosomeLength
     */
    public void setChromosomeLength(int chromosomeLength) {
        this.chromosomeLength = chromosomeLength;
    }

    /**
     *
     * @return
     */
    public int[] getCrossPoints() {
        return crossPoints;
    }

    /**
     *
     * @param crossPoints
     */
    public void setCrossPoints(int[] crossPoints) {
        if (crossPoints != null) {
            this.crossPoints = crossPoints;

            Arrays.sort(this.crossPoints);
            if (this.crossPoints[0] < 1 || this.crossPoints[crossPoints.length - 1] >= this.chromosomeLength) {
                throw new IllegalArgumentException("values in the crossPoints array must be\n\tbetween 1 and chromosomeLength-1 inclusive");
            }
        }
    }

    /**
     *
     * @param c1
     * @param c2
     * @return
     */
    public Chromosome crossOver(Chromosome c1, Chromosome c2) {
        
        Chromosome newChromosome = new Chromosome(c1.getChromosomeLength());
        Chromosome[] parentChromosomes = {c1, c2};

        for (int i = 0, start = 0; i <= crossPoints.length; i++) {
            int crossPoint = i == crossPoints.length ? c1.getChromosomeLength() : crossPoints[i];
            newChromosome.setAllele(start, parentChromosomes[i % 2].getAllele(start, crossPoint));
            start = crossPoint;
        }
        return newChromosome;

//        for (i = start = 0; i < crossPoints.length; i++) {
//            geneList.addAll(start, parentChromosomes[i % 2].getAllele(start, crossPoints[i]));
//            start = crossPoints[i];
//        }
//        
//        geneList.addAll(start, parentChromosomes[i % 2].getAllele(start, c1.getChromosomeLength()));
//        return new Chromosome(geneList);
    }
    
    /**
     *
     * @param c
     */
    public void mutate(Chromosome c) {
        
    }

    public void displayChromosomes() {
        genePool.stream().forEach((c) -> {
            System.out.println(c + " -> " + String.valueOf(c.value()));
        });
    }
}
