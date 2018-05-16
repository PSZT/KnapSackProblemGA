import java.util.*;
import java.util.List;


public class KnapsackAlgorithm {

    public Population processAlgorithm(Population population,List<Item> items,int capacityOfKnapsack){

        fitnessCalcAll(population.getChromosomeList(), items, capacityOfKnapsack);
        /*for (Chromosome chromosome:population.getChromosomeList()) {
            chromosome.fitnessCalculate(items, capacityOfKnapsack);
        }*/

        if(!population.ifMostHaveSameValue()){
            Population groupTemp = groupSelection(population);
            Population crossoverTemp = crossoverChromosomes(groupTemp,items,capacityOfKnapsack);
            Population mutateTemp = mutateChromosomes(crossoverTemp,items,capacityOfKnapsack);
            return  mutateTemp;
        }
        else
            return population;
    }

    public void fitnessCalcAll(List<Chromosome> chromosomes, List<Item> items, int capacityOfKnapsack) {
        for (Chromosome chromosome:chromosomes) {
            chromosome.fitnessCalculate(items, capacityOfKnapsack);
        }
    }

    //potem zmien na private
    public Population groupSelection(Population population) {
        List<Chromosome> chrGroupPopulation = new ArrayList<>(population.getChromosomeList());
        Collections.sort(chrGroupPopulation,new ChromosomeComparator());
        List<Chromosome> chrCrossOverPopulation = new ArrayList<>();
        int randomChromosomeIndex;
        Random rand = new Random();

        for(int i = 0; i < population.getPopulationSize(); i++ ) {
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
        Population newPop = new Population(chrCrossOverPopulation.size(),chrCrossOverPopulation);
        return newPop;
    }

    private Chromosome mutateChromosome(Chromosome chromosome,List<Item> items, int capacityOfKnapsack) {
        chromosome.setMutateGene(items);
        chromosome.fitnessCalculate(items,capacityOfKnapsack);
        return chromosome;
    }

    //potem zmien na private
    public Population mutateChromosomes(Population population, List<Item> items, int capacityOfKnapsack) {
        Random rand = new Random();

        List<Chromosome> newMutatePopulation = new ArrayList<>(population.getChromosomeList());

        for(int i = 0 ; i <  newMutatePopulation.size() ; i++) {

            int n = rand.nextInt(999);
            if(n == 500)
                mutateChromosome(newMutatePopulation.get(i),items,capacityOfKnapsack);
        }

        Population newPop = new Population(newMutatePopulation.size(),newMutatePopulation);
        return newPop;
    }

    //potem zmien na private
    public Population crossoverChromosomes(Population population, List<Item> items, int capacityOfKnapsack)
    {
        Random rand = new Random();
        List<Chromosome> chromosomeList = new ArrayList<>(population.getChromosomeList());
        int firstChrIndex = rand.nextInt(population.getPopulationSize()-1);
        int secondChrIndex = rand.nextInt(population.getPopulationSize()-1);
        int d = rand.nextInt(population.getPopulationSize()-1);

        Chromosome firstChr = chromosomeList.get(firstChrIndex);
        Chromosome secondChr = chromosomeList.get(secondChrIndex);

        int genesLength = firstChr.getGenes().length;
        int newGenes1[] = new int[firstChr.getGenes().length];
        int newGenes2[] = new int[firstChr.getGenes().length];

        List<Chromosome> newCrossoverPopulation = new ArrayList<>();

        for(int j =0 ; j< population.getPopulationSize()/2 ; j++) {
            for (int i = 0; i < genesLength; i++) {
                if (i < d) {
                    newGenes1[i] = firstChr.getGenes()[i];
                    newGenes2[i] = secondChr.getGenes()[i];
                } else {
                    newGenes1[i] = secondChr.getGenes()[i];
                    newGenes2[i] = firstChr.getGenes()[i];
                }
            }
            Chromosome chr1 = new Chromosome(genesLength, newGenes1);
            Chromosome chr2 = new Chromosome(genesLength, newGenes2);
            chr1.fitnessCalculate(items, capacityOfKnapsack);
            newCrossoverPopulation.add(chr1);
            chr2.fitnessCalculate(items, capacityOfKnapsack);
            newCrossoverPopulation.add(chr2);
        }
        Population newPop = new Population(newCrossoverPopulation.size(),newCrossoverPopulation);
        return newPop;
    }
}
