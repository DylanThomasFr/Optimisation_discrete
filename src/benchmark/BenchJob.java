package benchmark;

import algo.IAlgo;
import utils.Landscape;

import java.util.HashMap;

public class BenchJob implements Runnable{

    private final Landscape landscape;
    private final IAlgo algo;
    private final HashMap<Landscape, HashMap<IAlgo, Float>> returnFitness;
    private final int avgIter;

    public BenchJob(
            final Landscape landscape,
            final IAlgo algo,
            final HashMap<Landscape, HashMap<IAlgo, Float>> returnFitness,
            int avgIter
            ){
        this.landscape = landscape;
        this.algo = algo;
        this.returnFitness = returnFitness;
        this.avgIter = avgIter;
    }

    @Override
    public void run() {
        long fitness = 0;
        for (int i = 0; i < avgIter; i++) {
            fitness+= landscape.computeFitness(algo.compute(landscape));
        }
        fitness = Math.round(fitness / avgIter);
        returnFitness.get(landscape).put(algo, (float) fitness / landscape.getBestFitness());
    }
}
