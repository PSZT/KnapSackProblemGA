

import java.util.*;

public class Population {
    int populationSize;
    List<Chromosome> chromosomeList;

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

    //we calculate fitness for each chromosome
    public boolean chromosomesFitness(List<Item> items) {
        for (Chromosome chr : chromosomeList) {
            chr.fitnessCalculate(items);
        }
        if(checkEqualFitnessPercentage())
            return true;
        return false;
    }

    private boolean checkEqualFitnessPercentage() {
        List<Pair<Integer,Integer>> pairs = new ArrayList<>();
        double perc =  populationSize * 0.9;
        for(Pair p : pairs) {
            if((double)p.getLeft() > perc) {
                return true;
            }
        }
        return false;
    }

    private void checkFitness(List<Pair<Integer,Integer>> pairs){
        //List<Pair<Integer,Integer>> pairs = new ArrayList<>();
        for(Chromosome chr : chromosomeList) {
            if(pairs.size() == 0) {
                pairs.add(new Pair(chr.getChrTotalBenefit(),1));
            }
            else {
                int index=-1;
                for(int i=0;i<pairs.size();i++) {
                    if(pairs.get(i).getLeft().equals(chr.getChrTotalBenefit())) {
                        index = i;
                        i = pairs.size();
                    }
                }
                if(index > -1) {
                    pairs.get(index).setRight((int)pairs.get(index).getRight()+1);
                }
                else {
                    pairs.add(new Pair(chr.getChrTotalBenefit(),1));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Population{" +
                "chromosomeList=\n" + Arrays.toString(chromosomeList.toArray()) +
                '}';
    }
}
