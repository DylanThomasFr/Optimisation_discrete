package benchmark;

import algo.IAlgo;
import utils.Landscape;

import java.util.HashMap;

public class BenchJob implements Runnable{

    private final Landscape landscape;
    private final IAlgo algo;
    private final HashMap<Landscape, HashMap<IAlgo, Float>> returnFitness;
    private final HashMap<Landscape, HashMap<IAlgo, Long>> returnTimeComputed;
    private final int avgIter;

    public BenchJob(
            final Landscape landscape,
            final IAlgo algo,
            final HashMap<Landscape, HashMap<IAlgo, Float>> returnFitness,
            final HashMap<Landscape, HashMap<IAlgo, Long>> returnTimeComputed,
            int avgIter
            ){
        this.landscape = landscape;
        this.algo = algo;
        this.returnFitness = returnFitness;
        this.returnTimeComputed = returnTimeComputed;
        this.avgIter = avgIter;
    }

    @Override
    public void run() {
        long fitness = 0;
        long startTime;
        long stopTime;
        long time = 0;
        for (int i = 0; i < avgIter; i++) {
            startTime = System.nanoTime();
            fitness+= landscape.computeFitness(algo.compute(landscape));
            stopTime = System.nanoTime();
            time += stopTime - startTime;
        }
        fitness = Math.round(fitness / avgIter);
        returnFitness.get(landscape).put(algo, (float) fitness / landscape.getBestFitness());
        returnTimeComputed.get(landscape).put(algo, time / (avgIter * 1000));
    }
}
