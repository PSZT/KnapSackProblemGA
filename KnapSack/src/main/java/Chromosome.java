public class Chromosome {
    int size;
    byte [] genes;
    int chrTotalVolume;
    int chrTotalBenefit;


    int capacityOfKnapsack; //zeby nie wywalalo bledu. To jest calkowity rozmiar plecaka w funkcji fitnessCalculate

    public Chromosome(int size) {
        this.size = size;
        this.genes = new byte[size];
        for (int i:genes) {
            genes[i] = 0;
        }
    }

    int fitnessCalculate(Item [] items) {

        //wywal for each i daj normalne
        //for each item in chromosome
        for (Item i:items) {

            //if current item is included in knapsack, add its volume and benefit
            //to the total volume and benefit
            if(genes[i] == 1) {
                chrTotalVolume += items[i].getVolume();
                chrTotalBenefit += items[i].getBenefit();
            }

            if( chrTotalVolume > capacityOfKnapsack ) {

            }


        }
    }

}
