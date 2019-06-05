package algo;

import algo.mapping.IMapping;
import javafx.util.Pair;
import utils.Landscape;
import utils.Order;

import java.util.List;
import java.util.Objects;
import java.util.Random;

public class SimulatedAnnealing implements IAlgo {

    private final float mu;
    private final Order initialSolution;
    private final int movesByTemperature;

    private Random rand = new Random();
    private final IMapping mapping;

    public SimulatedAnnealing(final IMapping mapping, final float mu, final int movesByTemperature) {
        this(mapping, mu, movesByTemperature,null);
    }

    public SimulatedAnnealing(final IMapping mapping, final float mu, final int movesByTemperature, final Order initialSolution){
        this.mapping = mapping;
        this.mu = mu;
        this.initialSolution = initialSolution;
        this.movesByTemperature = movesByTemperature;
    }

    @Override
    public Order compute(Landscape landscape) {
        System.out.println("Compute "+this.toString());
        Order currentSolution;
        if(initialSolution == null){
            currentSolution = new Order(landscape.getSIZE());
            currentSolution.shuffle();
        } else currentSolution = initialSolution;

        Order bestSolution = currentSolution.clone();
        long bestFitness = landscape.computeFitness(bestSolution);
        Pair<Float, Integer> startingPoints = processTemperature0(landscape, currentSolution, 0.8D);

        Order solution;
        Order nextSolution;
        int maxChangesOfTemperature = startingPoints.getValue();
        float temperature = startingPoints.getKey();

        System.out.println("maxChangesOfTemperature = " + maxChangesOfTemperature);
        System.out.println("temperature = " + temperature);

        for (int k = 0; k < maxChangesOfTemperature; k++) {
            for (int l = 1; l < movesByTemperature; l++) {
                List<Order> neighbors = mapping.getNeighbors(currentSolution);
                solution = neighbors.get(rand.nextInt(neighbors.size())).clone();
                float deltaFitness = landscape.computeFitness(solution) - landscape.computeFitness(currentSolution);
                if(deltaFitness <= 0){
                    nextSolution = solution.clone();
                    if(landscape.computeFitness(nextSolution) < bestFitness){
                        bestSolution = nextSolution.clone();
                        bestFitness = landscape.computeFitness(bestSolution);
                    }
                } else {
                    float p = rand.nextFloat();
                    if(p < Math.exp(-deltaFitness / temperature)){
                        nextSolution = solution.clone();
                    } else {
                        nextSolution = currentSolution;
                    }
                }
                currentSolution = nextSolution;
            }
            temperature = mu * temperature;
        }
        return bestSolution;
    }

    private Pair<Float, Integer> processTemperature0(Landscape landscape, Order initialSolution, final double probaChances){
        long worstFitness = Long.MIN_VALUE;
        for (Order solution : mapping.getNeighbors(initialSolution)) {
            long fitness = landscape.computeFitness(solution);
            if(fitness > worstFitness){
                worstFitness = fitness;
            }
        }
        long deltaFitness = worstFitness - landscape.computeFitness(initialSolution);
        float temperature = -deltaFitness / (float) Math.log(probaChances);
        int temperatureChanges = getTemperatureChanges(deltaFitness, temperature, 0.01);
        return new Pair<>(temperature, temperatureChanges);
    }

    private int getTemperatureChanges(final float deltaFitness, float temperature0, final double proba){
        return (int) (Math.log(-deltaFitness / (temperature0 * Math.log(proba))) / Math.log(mu)) + 1;
    }

    @Override
    public String toString() {
        return "SimulatedAnnealing,"+mapping+
                ",mu,"+mu+
                ",movesByTemperature,"+movesByTemperature+
                ",initialSolution,"+(initialSolution == null ? "false" : "true");
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                "SimulatedAnnealing",
                mapping,
                mu,
                movesByTemperature,
                initialSolution
        );
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }
}
