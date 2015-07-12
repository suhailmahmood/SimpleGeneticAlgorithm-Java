package ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Suhail
 */
public class GenePool {

    private final List<Chromosome> genePool;
    private final int genePoolSize;
    private final int chromosomeLength;
    private final double crossOverRate;
    private final double mutationRate;
    private int[] crossPoints;

    public GenePool(int numOfChromosome, int chromosomeLength, double crossOverRate, double mutationRate) {

        this.genePoolSize = numOfChromosome;
        this.chromosomeLength = chromosomeLength > 0 ? chromosomeLength : 16;
        this.crossOverRate = crossOverRate;
        this.mutationRate = mutationRate;

        crossPoints = new int[1];
        crossPoints[0] = this.chromosomeLength / 2;

        genePool = new ArrayList<>();
        for (int i = 0; i < numOfChromosome; i++) {
            genePool.add(new Chromosome(chromosomeLength));
        }
    }

    public int getGenePoolSize() {
        return genePoolSize;
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

    public void mutateGenePool() {

        int totalGeneCount = genePoolSize * chromosomeLength;

        for (int i = 0; i < totalGeneCount; i++) {
            double prob = Math.random();
            if (prob < mutationRate) {
//                System.out.printf("Chromosome#: %d\tGene#: %d\n", i / chromosomeLength, i % chromosomeLength);
                genePool.get(i / chromosomeLength).getGeneAt(i % chromosomeLength).mutate();
            }
        }
    }

    public int getLeastFitIndex() {
        int index = 0;
        int min = genePool.get(index).value();
        int currentValue;
        for (int i = 1; i < genePoolSize; i++) {
            currentValue = genePool.get(i).value();
            if (currentValue < min) {
                index = i;
                min = currentValue;
            }
        }
        return index;
    }

    public void saveFittest(ArrayList<Chromosome> offsprings) {
        // sort in ascending order
        offsprings.sort(null);

        offsprings.stream().forEach((offspring) -> {
            int leastFitIndex = getLeastFitIndex();
            if (offspring.value() > genePool.get(leastFitIndex).value()) {
                genePool.set(leastFitIndex, offspring);
            }
        });
    }

    public void evolve(int noOfGeneration) {

        if (noOfGeneration == 0) {
            return;
        }

        ArrayList<Integer> selection = new ArrayList<>();

        for (int i = 0; i < genePoolSize; i++) {
            if (Math.random() <= crossOverRate) {
                selection.add(i);
            }
        }

        if (selection.size() % 2 == 1) {
            selection.remove(selection.size() - 1);
        }

        ArrayList<Chromosome> offsprings = new ArrayList<>();
        for (int i = 0; i < selection.size(); i += 2) {
            int index1 = selection.get(i);
            int index2 = selection.get(i + 1);
            offsprings.addAll(Arrays.asList(crossOver(genePool.get(index1), genePool.get(index2))));
        }

//        displayChromosomes();

        saveFittest(offsprings);

//        displayChromosomes();

        mutateGenePool();
        displayChromosomes();

        noOfGeneration--;
        evolve(noOfGeneration);
    }

    public void displayChromosomes() {
        genePool.stream().forEach((c) -> {
            System.out.println(c.value());
        });
        System.out.println("");
    }
}
