package algo;

import algo.mapping.MPermutation;
import utils.Landscape;
import utils.Order;

import java.util.List;
import java.util.Random;

public class HillClimbing implements IAlgo {

    private Random rand = new Random();
    private final int numberOfIterations;
    private MPermutation mapping = new MPermutation();

    public HillClimbing(int numberOfIterations){
        this.numberOfIterations = numberOfIterations;
    }

    @Override
    public Order compute(Landscape landscape) {
        if(numberOfIterations > landscape.getSIZE() * landscape.getSIZE())
            System.out.println("numberOfIterations very big");
        Order solution = new Order(landscape.getSIZE());
        Order bestSolution = solution.clone();
        Order bestLocalSolution;
        Order randomSolution = new Order(landscape.getSIZE());

        long bestFitness = Long.MAX_VALUE;
        for (int i = 0; i < numberOfIterations; i++) {
            bestLocalSolution = hillClimbingAlgo(landscape, randomSolution.shuffle());
            long fitness = landscape.computeFitness(bestLocalSolution);
            if(fitness < bestFitness){
                bestSolution = bestLocalSolution.clone();
                bestFitness = fitness;
            }
        }
        return bestSolution;
    }

    private Order hillClimbingAlgo(Landscape landscape, final Order startingSolution){
        long bestFitness = landscape.computeFitness(startingSolution);
        Order bestSolution = startingSolution;
        boolean thereIsBest;
        while(true) {
            thereIsBest = false;
            for (Order solution : mapping.getNeighbors(bestSolution)) {
                long fitness = landscape.computeFitness(solution);
                if(fitness < bestFitness){
                    bestSolution = solution.clone();
                    bestFitness = fitness;
                    thereIsBest = true;
                }
            }
            if(!thereIsBest)
                break;
        }
        return bestSolution;
    }
}
