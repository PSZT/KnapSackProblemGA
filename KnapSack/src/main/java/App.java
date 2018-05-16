import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App {
    String fileName;
    KnapsackAlgorithm knapsackAlgorithm = new KnapsackAlgorithm();
    Population population;
    List<Item> items;
    public App(String fileName) {
        this.fileName = fileName;
    }

    public Population calcuate() throws IOException {

        int counter = 0;

        ReadFromFile readFromFile = new ReadFromFile(fileName);
        readFromFile.readItems();

        //creating items
        items = new ArrayList<>();
        items = readFromFile.getItemArrayList();

        //creating population
        population = new Population(readFromFile.getPopSize());
        population.generateRandomPopulation(readFromFile.getItemArrayList().size());

        do {
            population = knapsackAlgorithm.processAlgorithm(population, readFromFile.getItemArrayList(),
                                                            readFromFile.getKnapsackCapacity());
            counter++;
            System.out.println(counter);
        } while ( !(counter > readFromFile.getGenNumber() && (population.ifMostHaveSameValue())));

        return population;
    }
}


//&& knapsackAlgorithm.ifMostHaveSameValue(chromosomeList)
//(counter > readFromFile.getGenNumber())