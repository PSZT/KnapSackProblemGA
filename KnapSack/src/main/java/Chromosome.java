import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class Chromosome {
    int size;
    int [] genes;
    int chrTotalVolume;
    int chrTotalBenefit;
    int capacityOfKnapsack=15; //zeby nie wywalalo bledu. To jest calkowity rozmiar plecaka w funkcji fitnessCalculate
    static SecureRandom rand;

    public Chromosome(int size) {
        this.rand = new SecureRandom();
        //rand.nextBytes(genes);
        this.size = size;
        this.genes = new int[size];

        for(int i:genes) {
            i = 0;
        }

        this.chrTotalBenefit = 0;
        this.chrTotalVolume = 0;
    }

    public void generateRandomChromosome() {
        for (int i=0 ; i<genes.length ; i++) {
            genes[i] = rand.nextInt(2);
        }

    }

    public int[] getGenes() {
        return genes;
    }

    public int getChrTotalVolume() {
        return chrTotalVolume;
    }

    public int getChrTotalBenefit() {
        return chrTotalBenefit;
    }

    public void fitnessCalculate(List<Item> items) {

        for (int i=0 ; i < size ; i++) {

            //if current item is included in knapsack, add its volume and benefit
            //to the total volume and benefit
            if (genes[i] == 1) {
                chrTotalVolume += items.get(i).getVolume();
                chrTotalBenefit += items.get(i).getBenefit();
            }

            if (chrTotalVolume > capacityOfKnapsack) {
                int n;
                //Randomly choose items from the chromosome
                //until we generate an item that is included in the knapsack
                do {
                    n = rand.nextInt(size);
                } while (genes[n] == 0);
                genes[n] = 0;
                chrTotalVolume = 0;
                chrTotalBenefit = 0;
                i = -1; //we have to check all items again
            }
        }
    }

    @Override
    public String toString() {
        System.out.println();
        return "Chromosome{" +

                ", genes=" + Arrays.toString(genes) +
                ", chrTotalVolume=" + chrTotalVolume +
                ", chrTotalBenefit=" + chrTotalBenefit +
                "}\n";

    }
}
