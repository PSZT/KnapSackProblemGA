import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.List;
import java.util.zip.CRC32;

public class KnapsackAlgorithm {

    public List<Chromosome> processAlgorithm(List<Chromosome> chrPopulation,List<Item> items,int capacityOfKnapsack){
        for (Chromosome chromosome:chrPopulation) {
            chromosome.fitnessCalculate(items, capacityOfKnapsack);
        }
        if(!ifMostHaveSameValue(chrPopulation)){
            List<Chromosome> groupTemp = groupSelection(chrPopulation);
            List <Chromosome> crossoverTemp = crossoverChromosome(groupTemp);
            return  crossoverTemp;
            //mutacja tutej
        }
        else
            return chrPopulation;

    }

    private List<Chromosome> groupSelection(List<Chromosome> chrPopulation) {
        List<Chromosome> chrGroupPopulation = new ArrayList<>(chrPopulation);
        Collections.sort(chrGroupPopulation,new ChromosomeComparator());
        List<Chromosome> chrCrossOverPopulation = new ArrayList<>();
        int randomChromosomeIndex;
        Random rand = new Random();

        for(int i = 0; i < chrPopulation.size(); i++ ) {
            int randomNumber  = rand.nextInt(99) + 1;
            Chromosome chosenChromosome;

            if (randomNumber >= 1 && randomNumber < 50) {

                randomChromosomeIndex = rand.nextInt(chrGroupPopulation.size() / 4);
                chosenChromosome = chrGroupPopulation.get(randomChromosomeIndex);


            } else if (randomNumber >= 50 && randomNumber < 80) {

                randomChromosomeIndex = rand.nextInt((chrGroupPopulation.size() / 2) - chrGroupPopulation.size() / 4) + chrGroupPopulation.size() / 4;
                chosenChromosome = chrGroupPopulation.get(randomChromosomeIndex);


            } else if (randomNumber >= 80 && randomNumber < 95) {
                randomChromosomeIndex = rand.nextInt((chrGroupPopulation.size()) - 3 * chrGroupPopulation.size() / 4) + chrGroupPopulation.size() / 2;
                chosenChromosome = chrGroupPopulation.get(randomChromosomeIndex);

            } else {

                randomChromosomeIndex = rand.nextInt(chrGroupPopulation.size() - 3 * chrGroupPopulation.size() / 4) + 3 * chrGroupPopulation.size() / 4;
                chosenChromosome = chrGroupPopulation.get(randomChromosomeIndex);


            }
            chrCrossOverPopulation.add(chosenChromosome);
        }
        return chrCrossOverPopulation;
    }

    public boolean ifMostHaveSameValue(List<Chromosome> fitnessValues) {
        ArrayList<Chromosome> tempArrayList = new ArrayList<>(fitnessValues);
        int currValue = fitnessValues.get(0).getChrTotalBenefit();
        int counter = 1;
        for (Chromosome chromosome : tempArrayList) {
            if (chromosome.getChrTotalBenefit()!= currValue)  {
                currValue = chromosome.getChrTotalBenefit();
                counter = 1;
            } else
                counter++;

            if (counter >= fitnessValues.size() * 0.9) {
                return true;
            }
        }
        return false;
    }

    private List<Chromosome> crossoverChromosome(List<Chromosome> population) {
        Random rand = new Random();

        int firstChrIndex = rand.nextInt(population.size());
        int secondChrIndex = rand.nextInt(population.size()-1);
        int d = rand.nextInt(population.size());

        Chromosome newChromosome, firstChr = population.get(firstChrIndex), secondChr = population.get(secondChrIndex);
        int genesLength = firstChr.getGenes().length;
        int newGenes1[] = new int[firstChr.getGenes().length];
        int newGenes2[] = new int[firstChr.getGenes().length];
        ArrayList<Chromosome> newCrossoverPopulation = new ArrayList<>();
        for(int j =0 ; j< population.size()/2 ; j++) {
            for (int i = 0; i < genesLength; i++) {
                if (i < d) {
                    newGenes1[i] = firstChr.getGenes()[i];
                    newGenes2[i] = secondChr.getGenes()[i];
                } else {
                    newGenes1[i] = secondChr.getGenes()[i];
                    newGenes2[i] = firstChr.getGenes()[i];
                }
            }
            Chromosome chr1 = new Chromosome(genesLength,newGenes1);
            Chromosome chr2 = new Chromosome(genesLength,newGenes2);
            newCrossoverPopulation.add(chr1);
            newCrossoverPopulation.add(chr2);
        }
        return newCrossoverPopulation;
    }
}
