import algo.HillClimbing;
import algo.RandomWalk;
import algo.SimulatedAnnealing;
import algo.TabuSearch;
import algo.mapping.IMapping;
import algo.mapping.MPermutation;
import algo.mapping.MSwapping;
import benchmark.Benchmark;
import io.BenchResultWriter;
import io.Parser;
import utils.Landscape;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int THREAD_NB = 4;
    private static final int AVERAGE_ITERATION = 4;

    static Landscape tai12a;
    static Landscape tai15a;
    static Landscape tai17a;
    static Landscape tai20a;
    static Landscape tai25a;
    static Landscape tai30a;
    static Landscape tai35a;
    static Landscape tai40a;
    static Landscape tai50a;
    static Landscape tai60a;
    static Landscape tai80a;
    static Landscape tai100a;

    public static void main(String[] args) throws Exception {


        MPermutation mapping = new MPermutation();
//        MSwapping mapping = new MSwapping();

        tai12a = Parser.createLandscape("data","tai12a.dat").setBestFitness(224416);
        tai15a = Parser.createLandscape("data","tai15a.dat").setBestFitness(388214);
        tai17a = Parser.createLandscape("data","tai17a.dat").setBestFitness(491812);
        tai20a = Parser.createLandscape("data","tai20a.dat").setBestFitness(703482);
        tai25a = Parser.createLandscape("data","tai25a.dat").setBestFitness(1167256);
        tai30a = Parser.createLandscape("data","tai30a.dat").setBestFitness(1818146);
        tai35a = Parser.createLandscape("data","tai35a.dat").setBestFitness(2422002);
        tai40a = Parser.createLandscape("data","tai40a.dat").setBestFitness(3139370);
        tai50a = Parser.createLandscape("data","tai50a.dat").setBestFitness(4938796);
        tai60a = Parser.createLandscape("data","tai60a.dat").setBestFitness(7205962);
        tai80a = Parser.createLandscape("data","tai80a.dat").setBestFitness(13499184);
        tai100a = Parser.createLandscape("data","tai100a.dat").setBestFitness(21044752);


        benchmarkRandomLogOverAll(mapping)
                .runBench()
                .writeOut(new BenchResultWriter("out", "benchmarkRandomLogOverAll"));

        benchmarkHillClimbingSmallOnes(mapping)
                .runBench()
                .writeOut(new BenchResultWriter("out", "benchmarkHillClimbingSmallOnes"));

        benchmarkHillClimbingBigOnes(mapping)
                .runBench()
                .writeOut(new BenchResultWriter("out", "benchmarkHillClimbingBigOnes"));

        benchmarkSimulatedAnnealingMuX(mapping)
                .runBench()
                .writeOut(new BenchResultWriter("out", "benchmarkSimulatedAnnealingY"));
//
        benchmarkTabou(mapping)
                .runBench()
                .writeOut(new BenchResultWriter("out", "benchmarkTabou"));
    }

    public static Benchmark benchmarkRandomLogOverAll(IMapping mapping) {
        Benchmark benchmark = new Benchmark(THREAD_NB, AVERAGE_ITERATION);
        benchmark.registerLandscape(tai12a);
        benchmark.registerLandscape(tai15a);
        benchmark.registerLandscape(tai17a);
        benchmark.registerLandscape(tai20a);
        benchmark.registerLandscape(tai25a);
        benchmark.registerLandscape(tai30a);
        benchmark.registerLandscape(tai35a);
        benchmark.registerLandscape(tai40a);
        benchmark.registerLandscape(tai50a);
        benchmark.registerLandscape(tai60a);
        benchmark.registerLandscape(tai80a);
        benchmark.registerLandscape(tai100a);
        for (int i = 1; i < 10001; i *= 10) {
            benchmark.registerAlgo(new RandomWalk(mapping, i));
        }
        return benchmark;
    }

    public static Benchmark benchmarkHillClimbingSmallOnes(IMapping mapping) {
        Benchmark benchmark = new Benchmark(THREAD_NB, AVERAGE_ITERATION);
        benchmark.registerLandscape(tai12a);
        benchmark.registerLandscape(tai15a);
        benchmark.registerLandscape(tai17a);
        benchmark.registerLandscape(tai20a);
        benchmark.registerLandscape(tai25a);
        benchmark.registerLandscape(tai30a);
        for (int i = 1; i < 30; i += 1) {
            benchmark.registerAlgo(new HillClimbing(mapping, 1, i));
        }
        for (int i = 30; i < 100 +1; i += 10) {
            benchmark.registerAlgo(new HillClimbing(mapping, 1, i));
        }
        return benchmark;
    }

    public static Benchmark benchmarkHillClimbingBigOnes(IMapping mapping) {
        Benchmark benchmark = new Benchmark(THREAD_NB, AVERAGE_ITERATION);
        benchmark.registerLandscape(tai12a);
        benchmark.registerLandscape(tai15a);
        benchmark.registerLandscape(tai17a);
        benchmark.registerLandscape(tai20a);
        benchmark.registerLandscape(tai25a);
        benchmark.registerLandscape(tai30a);
        benchmark.registerLandscape(tai35a);
        benchmark.registerLandscape(tai40a);
        benchmark.registerLandscape(tai50a);
        benchmark.registerLandscape(tai60a);
        benchmark.registerLandscape(tai80a);
        benchmark.registerLandscape(tai100a);
        for (int i = 1; i < 33; i += 3) {
            benchmark.registerAlgo(new HillClimbing(mapping, 0.05, i));
        }
        return benchmark;
    }


    public static Benchmark benchmarkSimulatedAnnealingMuX(IMapping mapping) {
        Benchmark benchmark = new Benchmark(THREAD_NB, AVERAGE_ITERATION);
        benchmark.registerLandscape(tai12a);
        benchmark.registerLandscape(tai15a);
        benchmark.registerLandscape(tai17a);
        benchmark.registerLandscape(tai20a);
        benchmark.registerLandscape(tai25a);
        benchmark.registerLandscape(tai30a);
        benchmark.registerLandscape(tai35a);
        benchmark.registerLandscape(tai40a);
        benchmark.registerLandscape(tai50a);
        benchmark.registerLandscape(tai60a);
        benchmark.registerLandscape(tai80a);
        benchmark.registerLandscape(tai100a);
        List<Float> mus = new ArrayList<>();
        mus.add(0.5F);
        mus.add(0.8F);
        mus.add(0.9F);
        mus.add(0.95F);
        mus.add(0.99F);

        List<Float> proboAcceptBadSolutions = new ArrayList<>();
        for (int i = 1; i < 10 ; i++) {
            proboAcceptBadSolutions.add(0.1F * i);
        }
        List<Float> probaAcceptThatBadSolutions = new ArrayList<>();
        for (int i = 1; i < 10 ; i++) {
            probaAcceptThatBadSolutions.add(0.01F * i);
        }
        for (int i = 1; i < 10 ; i++) {
            probaAcceptThatBadSolutions.add(0.1F * i);
        }
        for (int i = 1; i < 1000 + 1; i += 100) {
            for (Float mu : mus) {
                for (Float proboAcceptBadSolution : proboAcceptBadSolutions) {
                    for (Float probaAcceptThatBadSolution : probaAcceptThatBadSolutions) {
                        benchmark.registerAlgo(
                                new SimulatedAnnealing(mapping, mu, i, proboAcceptBadSolution, probaAcceptThatBadSolution)
                        );
                    }
                }
            }

        }
        return benchmark;
    }

    public static Benchmark benchmarkTabou(IMapping mapping) {
        Benchmark benchmark = new Benchmark(THREAD_NB, AVERAGE_ITERATION);
        benchmark.registerLandscape(tai12a);
        benchmark.registerLandscape(tai15a);
        benchmark.registerLandscape(tai17a);
        benchmark.registerLandscape(tai20a);
        benchmark.registerLandscape(tai25a);
        benchmark.registerLandscape(tai30a);
        benchmark.registerLandscape(tai35a);
        benchmark.registerLandscape(tai40a);
        benchmark.registerLandscape(tai50a);
        benchmark.registerLandscape(tai60a);
        benchmark.registerLandscape(tai80a);
        benchmark.registerLandscape(tai100a);
        for (int tabouSize = 1; tabouSize < 10; tabouSize += 1) {
            for (int maxSteps = 10; maxSteps < 10000 + 1; maxSteps *= 10) {
                benchmark.registerAlgo(new TabuSearch(mapping, maxSteps, tabouSize));
            }
        }
        for (int tabouSize = 10; tabouSize < 101; tabouSize += 10) {
            for (int maxSteps = 10; maxSteps < 10000 + 1; maxSteps *= 10) {
                benchmark.registerAlgo(new TabuSearch(mapping, maxSteps, tabouSize));
            }
        }
        return benchmark;
    }
}
