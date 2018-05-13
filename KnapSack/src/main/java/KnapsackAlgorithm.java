import java.util.*;

public class KnapsackAlgorithm {
    List<Item> itemList;




    public Chromosome groupSelection(ArrayList<Chromosome> chr) {



        int randomChromosomeIndex;
        Random rand = new Random();
        int randomNumber = rand.nextInt(99)+1;
        Chromosome chosenChromosome;

        if (randomNumber >= 0 && randomNumber < 50) {

            randomChromosomeIndex = rand.nextInt(chr.size() / 4);
            chosenChromosome = chr.get(randomChromosomeIndex);


        } else if (randomNumber >= 50 && randomNumber < 80) {

            randomChromosomeIndex = rand.nextInt((chr.size() / 2) - chr.size() / 4) + chr.size() / 4;
            chosenChromosome = chr.get(randomChromosomeIndex);


        }else if (randomNumber >= 80 && randomNumber < 95) {

            randomChromosomeIndex = rand.nextInt((chr.size()) - 3*chr.size() / 4) + chr.size() / 2;
            chosenChromosome = chr.get(randomChromosomeIndex);

        } else {

            randomChromosomeIndex = rand.nextInt(chr.size() - 3 * chr.size() / 4) + 3 * chr.size() / 4;
            chosenChromosome = chr.get(randomChromosomeIndex);

        }
        return chosenChromosome;
    }
}
