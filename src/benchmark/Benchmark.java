package benchmark;

import algo.IAlgo;
import io.BenchResultWriter;
import utils.Landscape;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Benchmark {

    private final int threadNb;
    private final int avgIter;
    private HashSet<Landscape> landscapes;
    private HashSet<IAlgo> algos;

    private HashMap<Landscape, HashMap<IAlgo, Float>> results;
    private HashMap<Landscape, HashMap<IAlgo, Long>> timeResults;

    public Benchmark(final int threadNb, final int avgIter){
        this.threadNb = threadNb;
        this.avgIter = avgIter;
        landscapes = new HashSet<>();
        algos = new HashSet<>();
        results = new HashMap<>();
        timeResults = new HashMap<>();
    }

    public Benchmark registerLandscape(Landscape landscape){
        landscapes.add(landscape);
        return this;
    }

    public Benchmark registerAlgo(IAlgo algo){
        algos.add(algo);
        return this;
    }

    public Benchmark runBench(){
        for (Landscape lanscape : landscapes) {
            results.put(lanscape, new HashMap<>());
            timeResults.put(lanscape, new HashMap<>());
            for (IAlgo algo : algos) {
                results.get(lanscape).put(algo, 0F);
                timeResults.get(lanscape).put(algo, 0L);
            }
        }

        ExecutorService es = Executors.newFixedThreadPool(threadNb);
        List<BenchJob> tasks = getBenchJobs();
        CompletableFuture<?>[] futures = tasks.stream()
                .map(task -> CompletableFuture.runAsync(task, es))
                .toArray(CompletableFuture[]::new);
        CompletableFuture.allOf(futures).join();
        es.shutdown();

        for (Landscape lanscape : landscapes) {
            for (IAlgo algo : algos) {
                System.out.println("results("+lanscape.getName()+")("+algo+") " + results.get(lanscape).get(algo) + " in " + timeResults.get(lanscape).get(algo));
            }
        }
        return this;
    }

    public Benchmark writeOut(BenchResultWriter benchResultWriter){
        List<String> lines = new ArrayList<>();
        for (Landscape lanscape : landscapes) {
            for (IAlgo algo : algos) {
                lines.add(lanscape.getName()+","+algo+","+results.get(lanscape).get(algo)+","+timeResults.get(lanscape).get(algo));
            }
        }
        benchResultWriter.write(lines);
        return this;
    }

    private List<BenchJob> getBenchJobs(){
        List<BenchJob> benchJobs = new ArrayList<>();
        for (Landscape landscape : landscapes) {
            for (IAlgo algo : algos) {
                benchJobs.add(
                        new BenchJob(
                                landscape,
                                algo,
                                results,
                                timeResults,
                                avgIter
                                )
                );
            }
        }
        return benchJobs;
    }
}
