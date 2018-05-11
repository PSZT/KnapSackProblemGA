import java.security.SecureRandom;

public class Chromosome {
    int size;
    byte [] genes;
    int chrTotalVolume;
    int chrTotalBenefit;


    int capacityOfKnapsack; //zeby nie wywalalo bledu. To jest calkowity rozmiar plecaka w funkcji fitnessCalculate

    static SecureRandom rand;

    public Chromosome(int size) {
        this.rand = new SecureRandom();
        rand.nextBytes(genes);
        this.size = size;
        this.genes = new byte[size];
        for (int i:genes) {
            genes[i] = 0;
        }
    }

    void generateChromosome() {
        rand.nextBytes(genes);
    }

    int fitnessCalculate(Item [] items) {

        for (int i=0 ; i < size ; i++) {

            //if current item is included in knapsack, add its volume and benefit
            //to the total volume and benefit
            if(genes[i] == 1) {
                chrTotalVolume += items[i].getVolume();
                chrTotalBenefit += items[i].getBenefit();
            }

            /*if( chrTotalVolume > capacityOfKnapsack ) {
                int n;
                do {

                } while ()
            }*/


        }
        return chrTotalBenefit;
    }

}
