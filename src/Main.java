import algo.HillClimbing;
import algo.RandomWalk;
import algo.SimulatedAnnealing;
import io.Parser;
import utils.Landscape;
import utils.Order;

public class Main {

    public static void main(String[] args) throws Exception {
        Landscape landscape = Parser.createLandscape("data/tai12a.dat");

        Order solutionOrder = new Order("8,1,6,2,11,10,3,5,9,7,12,4");

        long bestSolutionFitness = landscape.computeFitness(solutionOrder);
        System.out.println("bestSolutionFitness = " + bestSolutionFitness);

//        RandomWalk randomWalk = new RandomWalk(1000);
//        Order bestComputedOrder = randomWalk.compute(landscape);
//        System.out.println("bestComputedOrder = " + bestComputedOrder);
//        long bestComputedFitness = landscape.computeFitness(bestComputedOrder);
//        System.out.println("bestComputedFitness = " + bestComputedFitness);

//        HillClimbing hillClimbing = new HillClimbing(10000);
//        Order bestComputedOrder = hillClimbing.compute(landscape);
//        System.out.println("bestComputedOrder = " + bestComputedOrder);
//        long bestComputedFitness = landscape.computeFitness(bestComputedOrder);
//        System.out.println("bestComputedFitness = " + bestComputedFitness);


        SimulatedAnnealing simulatedAnnealing = new SimulatedAnnealing( 0.99F, 30000);
        Order bestComputedOrder = simulatedAnnealing.compute(landscape);
        System.out.println("bestComputedOrder = " + bestComputedOrder);
        long bestComputedFitness = landscape.computeFitness(bestComputedOrder);
        System.out.println("bestComputedFitness = " + bestComputedFitness);


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
