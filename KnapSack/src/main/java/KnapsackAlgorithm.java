import java.util.*;
import java.util.List;


public class KnapsackAlgorithm {

    public List<Chromosome> processAlgorithm(List<Chromosome> chrPopulation,List<Item> items,int capacityOfKnapsack){

        for (Chromosome chromosome:chrPopulation) {
            chromosome.fitnessCalculate(items, capacityOfKnapsack);
        }

        if(!ifMostHaveSameValue(chrPopulation)){
            List<Chromosome> groupTemp = groupSelection(chrPopulation);
            List <Chromosome> crossoverTemp = crossoverChromosome(groupTemp,items,capacityOfKnapsack);
            mutateChromosome(crossoverTemp,items,capacityOfKnapsack);
            return  crossoverTemp;
        }
        else
            return chrPopulation;
    }

    //potem zmien na private
    public List<Chromosome> groupSelection(List<Chromosome> chrPopulation) {
        List<Chromosome> chrGroupPopulation = new ArrayList<>();
        chrGroupPopulation = chrPopulation;
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
        List<Chromosome> tempArrayList = new ArrayList<>(fitnessValues);
        Collections.sort(tempArrayList,new ChromosomeComparator());
        int currValue = fitnessValues.get(0).getChrTotalBenefit();
        int counter = 1;
        for (Chromosome chromosome : tempArrayList) {
            if (chromosome.getChrTotalBenefit()!= currValue)  {
                currValue = chromosome.getChrTotalBenefit();
                counter = 1;
            } else
                counter++;

            if (counter >= fitnessValues.size() * 0.9)
                return true;
        }
        return false;
    }

    //potem zmien na private
    public List<Chromosome> mutateChromosome(List<Chromosome> population, List<Item> items, int capacityOfKnapsack) {
        List<Chromosome> newMutatePopulation = new ArrayList<>();
        //newMutatePopulation = population;
        for(int i = 0 ; i <  population.size() ; i++) {
            population.get(i).setMutateGene(items);
            population.get(i).fitnessCalculate(items, capacityOfKnapsack);
        }

        /*for(Chromosome chromosome: newMutatePopulation) {
            chromosome.setMutateGene(items);
            chromosome.fitnessCalculate(items, capacityOfKnapsack);
        }*/
        return population;
    }

    //potem zmien na private
    public List<Chromosome> crossoverChromosome(List<Chromosome> population, List<Item> items, int capacityOfKnapsack)
    {
        Random rand = new Random();

        int firstChrIndex = rand.nextInt(population.size()-1);
        int secondChrIndex = rand.nextInt(population.size()-1);
        int d = rand.nextInt(population.size());

        Chromosome firstChr = population.get(firstChrIndex), secondChr = population.get(secondChrIndex);
        int genesLength = firstChr.getGenes().length;
        int newGenes1[] = new int[firstChr.getGenes().length];
        int newGenes2[] = new int[firstChr.getGenes().length];
        List<Chromosome> newCrossoverPopulation = new ArrayList<>();
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
            chr1.fitnessCalculate(items, capacityOfKnapsack);
            newCrossoverPopulation.add(chr1);
            chr2.fitnessCalculate(items, capacityOfKnapsack);
            newCrossoverPopulation.add(chr2);
        }
        return newCrossoverPopulation;
    }
}
