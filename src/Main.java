import algo.HillClimbing;
import algo.RandomWalk;
import algo.SimulatedAnnealing;
import algo.TabuSearch;
import algo.mapping.MPermutation;
import benchmark.Benchmark;
import io.BenchResultWriter;
import io.Parser;
import utils.Landscape;
import utils.Order;

public class Main {

    public static void main(String[] args) throws Exception {

        MPermutation mapping = new MPermutation();

        Benchmark benchmark = new Benchmark(2, 10);
        benchmark.registerLandscape(Parser.createLandscape("data","tai12a.dat").setBestFitness(224416L));
        benchmark.registerLandscape(Parser.createLandscape("data","tai20a.dat").setBestFitness(703482L));
        for (int i = 1; i < 100000; i *= 2) {
            benchmark.registerAlgo(new RandomWalk(mapping, i));
        }
        for (int i = 1; i < 145; i += 1) {
            benchmark.registerAlgo(new HillClimbing(mapping, i));
        }

        BenchResultWriter benchResultWriter = new BenchResultWriter("out", "test2");

        benchmark.runBench();
        benchmark.writeOut(benchResultWriter);




//        Landscape landscape = Parser.createLandscape("data/tai12a.dat");
//        Order solutionOrder = new Order("8,1,6,2,11,10,3,5,9,7,12,4");


//        Landscape landscape = Parser.createLandscape("data/tai40a.dat");
//        System.out.println("landscape = " + landscape);
//        Order solutionOrder = new Order("8,16,14,17,4,11,3,19,7,9,1,15,6,13,10,2,5,20,18,12");
//
//
//        long bestSolutionFitness = landscape.computeFitness(solutionOrder);
//        System.out.println("bestSolutionFitness = " + bestSolutionFitness);

//        RandomWalk randomWalk = new RandomWalk(10000);
//        Order bestComputedOrder = randomWalk.compute(landscape);
//        System.out.println("bestComputedOrder = " + bestComputedOrder);
//        long bestComputedFitness = landscape.computeFitness(bestComputedOrder);
//        System.out.println("bestComputedFitness = " + bestComputedFitness);

//        HillClimbing hillClimbing = new HillClimbing();
//        Order bestComputedOrder = hillClimbing.compute(landscape);
//        System.out.println("bestComputedOrder = " + bestComputedOrder);
//        long bestComputedFitness = landscape.computeFitness(bestComputedOrder);
//        System.out.println("bestComputedFitness = " + bestComputedFitness);


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
