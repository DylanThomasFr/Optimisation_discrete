import algo.HillClimbing;
import algo.RandomWalk;
import algo.SimulatedAnnealing;
import algo.TabuSearch;
import io.Parser;
import utils.Landscape;
import utils.Order;

public class Main {

    public static void main(String[] args) throws Exception {
//        Landscape landscape = Parser.createLandscape("data/tai12a.dat");
//        Order solutionOrder = new Order("8,1,6,2,11,10,3,5,9,7,12,4");


        Landscape landscape = Parser.createLandscape("data/tai20a.dat");
        Order solutionOrder = new Order("10,9,12,20,19,3,14,6,17,11,5,7,15,16,18,2,4,8,13,1");


        long bestSolutionFitness = landscape.computeFitness(solutionOrder);
        System.out.println("bestSolutionFitness = " + bestSolutionFitness);

//        RandomWalk randomWalk = new RandomWalk(10000);
//        Order bestComputedOrder = randomWalk.compute(landscape);
//        System.out.println("bestComputedOrder = " + bestComputedOrder);
//        long bestComputedFitness = landscape.computeFitness(bestComputedOrder);
//        System.out.println("bestComputedFitness = " + bestComputedFitness);

        HillClimbing hillClimbing = new HillClimbing();
        Order bestComputedOrder = hillClimbing.compute(landscape);
        System.out.println("bestComputedOrder = " + bestComputedOrder);
        long bestComputedFitness = landscape.computeFitness(bestComputedOrder);
        System.out.println("bestComputedFitness = " + bestComputedFitness);


//        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing( 0.999F, 100);
//        Order bestComputedOrder = simulatedAnnealing.compute(landscape);
//        System.out.println("bestComputedOrder = " + bestComputedOrder);
//        long bestComputedFitness = landscape.computeFitness(bestComputedOrder);
//        System.out.println("bestComputedFitness = " + bestComputedFitness);

//        TabuSearch tabuSearch = new TabuSearch( 500, 22);
//        Order bestComputedOrder = tabuSearch.compute(landscape, new Order(landscape.getSIZE()));
//        System.out.println("bestComputedOrder = " + bestComputedOrder);
//        long bestComputedFitness = landscape.computeFitness(bestComputedOrder);
//        System.out.println("bestComputedFitness = " + bestComputedFitness);


//        int i = 1000000;
//        int newValue;
//        while(i-- > 0){
//            Collections.shuffle(order);
//            newValue = landscape.computeFitness(order);
//            if(newValue < best)
//                System.out.println("newValue = " + newValue);
////            System.out.println("landscape.computeFitness() = " + newValue + " " + (newValue < best));
//        }
    }
}
