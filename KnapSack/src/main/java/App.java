import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    String fileName;
    List<Chromosome> chromosomeList = new ArrayList<>();
    KnapsackAlgorithm knapsackAlgorithm = new KnapsackAlgorithm();
    public App(String fileName) {
        this.fileName = fileName;
    }

    public List<Chromosome> calcuate() throws IOException {
        int counter =0;
        ReadFromFile readFromFile = new ReadFromFile(fileName);
        readFromFile.readItems();
        for(int i = 0 ; i < readFromFile.getPopSize() ; i++) {
            Chromosome chr = new Chromosome(readFromFile.getItemArrayList().size());
            chr.generateRandomChromosome();
            chromosomeList.add(chr);
        }
        do {
            List<Chromosome> tempList = knapsackAlgorithm.processAlgorithm(chromosomeList,readFromFile.getItemArrayList(),
                    readFromFile.getBackPackCapacity());
            chromosomeList = tempList;
            counter++;
        }while (counter <= readFromFile.getGenNumber() && knapsackAlgorithm.ifMostHaveSameValue(chromosomeList));
        return chromosomeList;
    }
}

