package algo;

import algo.mapping.IMapping;
import utils.Landscape;
import utils.Order;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class HillClimbing implements IAlgo {

    private Random rand = new Random();
    private final int numberOfIterations;
    private final double percentOfNeighbors;
    private final IMapping mapping;

    public HillClimbing(final IMapping mapping, final double percentOfNeighbors, final int numberOfIterations){
        this.mapping = mapping;
        this.numberOfIterations = numberOfIterations;
        this.percentOfNeighbors = percentOfNeighbors;
    }

    public HillClimbing(final IMapping mapping, final double percentOfNeighbors){
        this(mapping, percentOfNeighbors, 0);
    }

    @Override
    public Order compute(Landscape landscape) {
        System.out.println("Compute "+landscape.getName()+" with "+this.toString());
        int numberOfIterationsToRun = numberOfIterations;
        if(numberOfIterationsToRun == 0){
            numberOfIterationsToRun = 2 * landscape.getSIZE() * landscape.getSIZE();
        } else {
            if(numberOfIterationsToRun > landscape.getSIZE() * landscape.getSIZE())
                System.out.printf(
                        "numberOfIterations very big : %d/%d%n",
                        numberOfIterations,
                        landscape.getSIZE() * landscape.getSIZE());
        }
        Order solution = new Order(landscape.getSIZE());
        Order bestSolution = solution.clone();
        Order bestLocalSolution;
        Order randomSolution = new Order(landscape.getSIZE());

        long bestFitness = Long.MAX_VALUE;
        for (int i = 0; i < numberOfIterationsToRun; i++) {
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

        Integer landscapeSize = 2 * landscape.getSIZE();
        while(landscapeSize-- >= 0) {
            List<Order> neighbors = mapping.getNeighbors(bestSolution);
            Collections.shuffle(neighbors);
            int max = (int) (neighbors.size() * percentOfNeighbors) + 1;

            boolean thereisNoBest = true;
            for (Order solution : neighbors) {
                if(max-- == 0){
                    break;
                }
                long fitness = landscape.computeFitness(solution);
                if(fitness < bestFitness){
                    bestSolution = solution.clone();
                    bestFitness = fitness;
                    thereisNoBest = false;
                }
            }
            if(thereisNoBest)
                break;
        }
        return bestSolution;
    }

    @Override
    public String toString() {
        return "HillClimbing," + mapping+
                ",numberOfIterations,"+numberOfIterations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                "HillClimbing",
                mapping,
                numberOfIterations
        );
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }
}
