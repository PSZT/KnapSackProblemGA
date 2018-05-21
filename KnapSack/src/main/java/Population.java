import java.util.*;

public class Population {
    int populationSize;
    List<Chromosome> chromosomeList;

    public Population() {
        this.populationSize = 0;
        chromosomeList = new ArrayList<>();
    }

    public Population(int populationSize) {
        this.populationSize = populationSize;
        chromosomeList = new ArrayList<>();
    }

    public Population(int populationSize, List<Chromosome> chromosomeList) {
        this.populationSize = populationSize;
        this.chromosomeList = chromosomeList;
    }

    public void generateRandomPopulation(int chrSize) {
        for(int i = 0 ; i < populationSize ; i++) {
            Chromosome chr = new Chromosome(chrSize);
            chr.generateRandomChromosome();
            chromosomeList.add(chr);
        }
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public List<Chromosome> getChromosomeList() {
        return chromosomeList;
    }

    //we calculate fitness for each chromosome
    /*public boolean chromosomesFitness(List<Item> items,int capacityOfKnapsack) {
        for (Chromosome chr : chromosomeList) {
            chr.fitnessCalculate(items,capacityOfKnapsack);
        }
        if(ifMostHaveSameValue())
            return true;
        return false;
    }*/

    public boolean ifMostHaveSameValue() {

        List<Chromosome> tempArrayList = new ArrayList<>(chromosomeList);
        Collections.sort(tempArrayList,new ChromosomeComparator());
        int currValue = chromosomeList.get(0).getChrTotalBenefit();
        int counter = 1;
        for (Chromosome chromosome : tempArrayList) {
            if (chromosome.getChrTotalBenefit()!= currValue)  {
                currValue = chromosome.getChrTotalBenefit();
                counter = 1;
            } else
                counter++;

            if (counter >= chromosomeList.size() * 0.9)
                return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return "Population{" +
                "chromosomeList=\n" + Arrays.toString(chromosomeList.toArray()) +
                '}';
    }
}
