import java.lang.reflect.Array;
import java.util.*;

public class KnapsackAlgorithm {
    List<Item> itemList;

    public static void main(String[] args) {
        ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,4,4,4,4,4,5,5,5,5,5,5,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8));
        System.out.println(ints.size());
        KnapsackAlgorithm k = new KnapsackAlgorithm();
        System.out.println(k.ifMostHaveSameValue(ints));
    }


    public ArrayList<Chromosome> groupSelection(ArrayList<Chromosome> chr) {
        Collections.sort(chr);
        int randomChromosomeIndex;
        Random rand = new Random();
        int randomNumber = rand.nextInt(99) + 1;
        Chromosome chosenChromosome;

        if (randomNumber >= 1 && randomNumber < 50) {

            randomChromosomeIndex = rand.nextInt(chr.size() / 4);
            chosenChromosome = chr.get(randomChromosomeIndex);


        } else if (randomNumber >= 50 && randomNumber < 80) {

            randomChromosomeIndex = rand.nextInt((chr.size() / 2) - chr.size() / 4) + chr.size() / 4;
            chosenChromosome = chr.get(randomChromosomeIndex);


        } else if (randomNumber >= 80 && randomNumber < 95) {

            randomChromosomeIndex = rand.nextInt((chr.size()) - 3 * chr.size() / 4) + chr.size() / 2;
            chosenChromosome = chr.get(randomChromosomeIndex);

        } else {

            randomChromosomeIndex = rand.nextInt(chr.size() - 3 * chr.size() / 4) + 3 * chr.size() / 4;
            chosenChromosome = chr.get(randomChromosomeIndex);

        }
        return chosenChromosome;
    }

    public boolean ifMostHaveSameValue(ArrayList<Chromosome> fitnessValues) {
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

    public Chromosome crossoverChromosome(ArrayList<Chromosome> chromosomes) {
        Random rand = new Random();
        int firstChrIndex = rand.nextInt(chromosomes.size());
        int secondChrIndex = rand.nextInt(chromosomes.size());
        Chromosome newChromosome, firstChr = chromosomes.get(firstChrIndex), secondChr = chromosomes.get(secondChrIndex);
        int genesLength = firstChr.genes.length;
        int newGenes[] = new int[firstChr.genes.length];
        for (int i = 0; i < genesLength; i++) {
            if (i < genesLength/2)
                newGenes[i]=firstChr.genes[i];
            else
                newGenes[i]=secondChr.genes[i];
        }
        return newChromosome = new Chromosome(newGenes);

    }
}
