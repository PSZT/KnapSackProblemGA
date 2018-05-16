import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class Chromosome {
    private int size;
    private int [] genes;
    private int chrTotalVolume;
    private int chrTotalBenefit;
    private static SecureRandom rand;

    public Chromosome(int size) {
        this.rand = new SecureRandom();
        this.size = size;
        this.genes = new int[size];

        for(int i:genes) {
            i = 0;
        }

        this.chrTotalBenefit = 0;
        this.chrTotalVolume = 0;
    }

    public Chromosome(int size, int chrTotalVolume, int chrTotalBenefit) {
        this.size = size;
        this.chrTotalVolume = chrTotalVolume;
        this.chrTotalBenefit = chrTotalBenefit;
    }

    //dodalem to z randem na potrzeby testow, ktore sobie robiłem
    public Chromosome(int size,int[] newGenes) {
        this.size = size;
        this.genes = newGenes;
        this.rand = new SecureRandom(); //na potrzeby mojego testowania
        this.chrTotalBenefit = 0;
        this.chrTotalVolume = 0;
    }

    public void generateRandomChromosome() {
        for (int i=0 ; i<genes.length ; i++) {
            genes[i] = rand.nextInt(2);
        }
    }

    public void setMutateGene(List<Item> items) {
        int n;
        n = rand.nextInt(size-1);
        //jak zmienimy wartosc danego bitu to trzeba zmniejszyc/zwiekszyc volume i benefit calego chromosomu
        if(genes[n] == 1) {
            genes[n] = 0;
            chrTotalBenefit -= items.get(n).getBenefit();
            chrTotalVolume -= items.get(n).getVolume();
        }
        else{
                genes[n] = 1;
            chrTotalBenefit += items.get(n).getBenefit();
            chrTotalVolume += items.get(n).getVolume();
        }
        //chrTotalBenefit = 0;
        //chrTotalVolume = 0;
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

    public void fitnessCalculate(List<Item> items,int capacityOfKnapsack) {

        chrTotalVolume = 0;
        chrTotalBenefit = 0;

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
                while(true) {
                    n = rand.nextInt(size-1);
                    if(genes[n] == 1) {
                        genes[n] = 0;
                        break;
                    }
                }
                //while (genes[n] == 0);
                //System.out.println("Liczymy fitness. Usuwamy z plecaka element nr " + (n+1));
                //genes[n] = 0;
                chrTotalVolume = 0;
                chrTotalBenefit = 0;
                i = -1; //we have to check all items again
            }
        }
    }

    @Override
    public String toString() {
        //System.out.println();
        return "Chromosome{" +

                "genes=" + Arrays.toString(genes) +
                ", chrTotalVolume=" + chrTotalVolume +
                ", chrTotalBenefit=" + chrTotalBenefit +
                "}\n";

    }
}
