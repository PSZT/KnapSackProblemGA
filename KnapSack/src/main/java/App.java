import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String args[]) {
        App app = new App("KnapSack/src/main/resources/FirstSet");
        try {
            Population population = app.calculate();
            System.out.println(population.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String fileName;
    KnapsackAlgorithm knapsackAlgorithm = new KnapsackAlgorithm();
    Population population;
    List<Item> items;

    public App(String fileName) {
        this.fileName = fileName;
    }

    public Population calculate() throws IOException {

        int counter = 0;

        FileParser fileParser = new FileParser(fileName);
        fileParser.readItems();

        //creating items
        items = new ArrayList<>();
        items = fileParser.getItemArrayList();

        //creating population
        population = new Population(fileParser.getPopSize());
        population.generateRandomPopulation(fileParser.getItemArrayList().size());

        do {
            population = knapsackAlgorithm.processAlgorithm(population, fileParser.getItemArrayList(),
                    fileParser.getKnapsackCapacity());
            counter++;
            System.out.println(counter);
        } while (!(counter > fileParser.getGenNumber() && (population.ifMostHaveSameValue())));

        return population;
    }
}
