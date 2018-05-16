import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    String fileName;
    List<Chromosome> chromosomeList = new ArrayList<>();
    KnapsackAlgorithm knapsackAlgorithm = new KnapsackAlgorithm();
    Population population;
    List<Item> items;
    public App(String fileName) {
        this.fileName = fileName;
    }

    public Population calcuate() throws IOException {
        int counter =0;
        ReadFromFile readFromFile = new ReadFromFile(fileName);
        readFromFile.readItems();
        //System.out.println(readFromFile.toString());

        items = readFromFile.getItemArrayList();


        population = new Population(readFromFile.getPopSize());
        population.generateRandomPopulation(readFromFile.getItemArrayList().size());

        //System.out.println(items.get(0).getBenefit());
        //do



        /*
        //we create random population
        for(int i = 0 ; i < readFromFile.getPopSize() ; i++) {
            Chromosome chr = new Chromosome(readFromFile.getItemArrayList().size());
            chr.generateRandomChromosome();
            chromosomeList.add(chr);
        }*/

        do {
            population = knapsackAlgorithm.processAlgorithm(population,
                    readFromFile.getItemArrayList(), readFromFile.getKnapsackCapacity());
            counter++;
            System.out.println(counter);
        } while ( !(counter > readFromFile.getGenNumber() && (population.ifMostHaveSameValue())));
        return population;
    }
}


//&& knapsackAlgorithm.ifMostHaveSameValue(chromosomeList)
//(counter > readFromFile.getGenNumber())