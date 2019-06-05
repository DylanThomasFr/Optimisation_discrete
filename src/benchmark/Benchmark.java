package benchmark;

import algo.IAlgo;
import algo.mapping.IMapping;
import javafx.util.Pair;
import utils.Landscape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.*;

public class Benchmark {

    private final int avgIter;
    private HashSet<Landscape> landscapes;
    private HashSet<IAlgo> algos;

    private HashMap<Landscape, HashMap<IAlgo, Long>> results;

    public Benchmark(int avgIter){
        this.avgIter = avgIter;
//        returnFirtness = new LinkedBlockingQueue<>();
        results = new HashMap<>();
    }

    public Benchmark registerLandscape(Landscape landscape){
        landscapes.add(landscape);
        return this;
    }

    public Benchmark registerAlgo(IAlgo algo){
        algos.add(algo);
        return this;
    }

    public void runBench(){
        for (Landscape lanscape : landscapes) {
            results.put(lanscape, new HashMap<>());
            for (IAlgo algo : algos) {
                results.get(lanscape).put(algo, 0L);
            }
        }

        ExecutorService es = Executors.newFixedThreadPool(4);
        List<BenchJob> tasks = getBenchJobs();
        CompletableFuture<?>[] futures = tasks.stream()
                .map(task -> CompletableFuture.runAsync(task, es))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        es.shutdown();

        for (Landscape lanscape : landscapes) {
            for (IAlgo algo : algos) {
                System.out.println("results("+lanscape+")("+algo+") " + results.get(lanscape).get(algo));
            }
        }
    }

    public List<BenchJob> getBenchJobs(){
        List<BenchJob> benchJobs = new ArrayList<>();
        for (Landscape landscape : landscapes) {
            for (IAlgo algo : algos) {
                benchJobs.add(
                        new BenchJob(
                                landscape,
                                algo,
                                results,
                                avgIter
                                )
                );
            }
        }
        return benchJobs;
    }
}
