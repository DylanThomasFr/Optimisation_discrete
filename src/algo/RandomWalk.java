package algo;

import algo.mapping.IMapping;
import utils.Landscape;
import utils.Order;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class RandomWalk implements IAlgo {

    private Random rand = new Random();
    private final int numberOfIterations;
    private IMapping mapping;

    public RandomWalk(IMapping mapping, final int numberOfIterations){
        this.mapping = mapping;
        this.numberOfIterations = numberOfIterations;
    }

    @Override
    public Order compute(Landscape landscape) {
        Order solution = new Order(landscape.getSIZE());
        Order bestSolution = solution.clone();

        long bestFitness = Long.MAX_VALUE;
        for (int i = 0; i < numberOfIterations; i++) {
            List<Order> neighbors = mapping.getNeighbors(solution);
            int pickedOne = rand .nextInt(neighbors.size());
            solution = neighbors.get(pickedOne).clone();
            long fitness = landscape.computeFitness(solution);
            if(fitness < bestFitness){
                bestSolution = solution.clone();
                bestFitness = fitness;
            }
        }
        return bestSolution;
    }

    @Override
    public IAlgo setMappingStrategy(IMapping mapping) {
        this.mapping = mapping;
        return this;
    }

    @Override
    public String toString() {
        return "RandomWalk."+mapping+
                ".numberOfIterations."+numberOfIterations;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                "RandomWalk",
                mapping,
                numberOfIterations
        );
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }
}
