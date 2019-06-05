package benchmark;

import java.util.LinkedList;

public class BenchPool {

    private final int threadNumber;
    LinkedList<Benchmark> benchmarks;

    public BenchPool(int threadNumber){
        this.threadNumber = threadNumber;
        benchmarks = new LinkedList<>();
    }

    public BenchPool registerBenchMark(Benchmark benchmark){
        benchmarks.add(benchmark);
        return this;
    }

    public void runBenchPool(){

    }

}
