package ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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

    public Chromosome getChromosomeAt(int index) {
        return genePool.get(index);
    }

    public void setChromosomeAt(int index, Chromosome c) {
        genePool.set(index, c);
    }

    public int getChromosomeLength() {
        return chromosomeLength;
    }

    public void setChromosomeLength(int chromosomeLength) {
        genePool.parallelStream().forEach((Chromosome c) -> {
            c.setChromosomeLength(chromosomeLength);
        });
        this.chromosomeLength = chromosomeLength;
    }

    public int[] getCrossPoints() {
        return crossPoints;
    }

    public void setCrossPoints(int[] crossPoints) {
        if (crossPoints != null) {
            this.crossPoints = crossPoints;

            Arrays.sort(this.crossPoints);
            if (this.crossPoints[0] < 1 || this.crossPoints[crossPoints.length - 1] >= this.chromosomeLength) {
                throw new IllegalArgumentException("values in the crossPoints array must be\n\tbetween 1 and chromosomeLength-1 inclusive");
            }
        }
    }

    public Chromosome[] crossOver(Chromosome c1, Chromosome c2) {

        Chromosome[] offsprings = new Chromosome[2];
        offsprings[0] = new Chromosome(c1.getChromosomeLength());
        offsprings[1] = new Chromosome(c1.getChromosomeLength());

        Chromosome newChromosome = new Chromosome(c1.getChromosomeLength());
        Chromosome[] parentChromosomes = {c1, c2};

        int selector = 0;
        for (int i = 0, start = 0; i <= crossPoints.length; i++) {

            int crossPoint = i == crossPoints.length ? c1.getChromosomeLength() : crossPoints[i];

            offsprings[0].setAllele(start, parentChromosomes[selector].getAllele(start, crossPoint));
            offsprings[1].setAllele(start, parentChromosomes[1 - selector].getAllele(start, crossPoint));
            selector = 1 - selector;
            start = crossPoint;
        }
        return offsprings;
    }

    public void mutate() {
        int genePoolSize = genePool.size();
        int totalGeneCount = genePoolSize * chromosomeLength;
        
        for (int i = 0; i < totalGeneCount; i++) {
            if(Math.random() < mutationRate) {
                genePool.get(i / genePoolSize).getGeneAt(i % genePoolSize).mutate();
            }
        }

    }
    
    private int getLeastFitIndex() {
        int index = 0;
        int min = genePool.get(index).value();
        int currentValue;
        for (int i = 1; i < genePool.size(); i++) {
            currentValue = genePool.get(i).value();
            if(currentValue < min) {
                index = i;
                min = currentValue;
            }
        }
        return index;
    }
    
    public void saveFittest(ArrayList<Chromosome> offsprings) {
        
        offsprings.sort(null);
        
        int leastIndex;
        for (Chromosome offspring : offsprings) {
            leastIndex = getLeastFitIndex();
            
            if(offspring.value() > genePool.get(leastIndex).value()) {
                genePool.set(leastIndex, offspring);
            }
        }
    }

    public void evolve(int noOfGeneration) {
        
        int chromosomeCount = genePool.size();
        ArrayList<Integer> selection = new ArrayList<>();
        int randID;
        
        for (int i = 0; i < chromosomeCount; i++) {
            if (Math.random() <= crossOverRate) {
                selection.add(i);
            }
        }
        
        if(selection.size() % 2 == 1) {
            selection.add((int) (Math.random() * chromosomeCount));
        }
        
        ArrayList<Chromosome> offsprings = new ArrayList<>();
        for(int i=0; i<selection.size(); i+=2) {
            offsprings.addAll(Arrays.asList(crossOver(genePool.get(i), genePool.get(i+1))));
        }
        
        
    }

    public void displayChromosomes() {
        genePool.stream().forEach((c) -> {
            System.out.println(c + " -> " + String.valueOf(c.value()));
        });
    }
    
    public void sort() {
        genePool.sort(null);
    }
}
