package algo;

import algo.mapping.IMapping;
import algo.operation.IOperation;
import algo.operation.ONoChangeOperation;
import javafx.util.Pair;
import utils.Landscape;
import utils.Order;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Queue;

public class TabuSearch implements IAlgo {

    private final int maxSteps;
    private final int tabouSize;
    private final IMapping mapping;

    public TabuSearch(final IMapping mapping, final int maxSteps, final int tabouSize){
        this.mapping = mapping;
        this.maxSteps = maxSteps;
        this.tabouSize = tabouSize;
    }

    @Override
    public Order compute(Landscape landscape) {
        return compute(landscape, new Order(landscape.getSIZE()).shuffle());
    }

    public Order compute(Landscape landscape, Order initialSolution){
        System.out.println("Compute "+this.toString());
        Order bestSolution = initialSolution.clone();
        long bestFitness = landscape.computeFitness(bestSolution);

        Queue<IOperation> tabouList = new LinkedList<>();

        Order currentSolution = initialSolution.clone();
        List<Pair<Order, IOperation>> neighbors;
        for (int i = 0; i < maxSteps; i++) {
            neighbors = mapping.getNeighborsExceptOperations(currentSolution, (List) tabouList);
            long newBestFitness = Long.MAX_VALUE;
            Order newBestSolution = currentSolution.clone();
            IOperation reverseOperation = new ONoChangeOperation();
            for (Pair<Order, IOperation> pair : neighbors) {
                long tmpFitness = landscape.computeFitness(pair.getKey());
                if(tmpFitness < newBestFitness){
                    newBestFitness = tmpFitness;
                    newBestSolution = pair.getKey().clone();
                    reverseOperation = pair.getValue().getReverseOperation();
                }
            }

            long currentFitness = landscape.computeFitness(currentSolution);
            long deltaFitness = newBestFitness - currentFitness;
            if(deltaFitness >= 0){
                tabouListAdd(reverseOperation,tabouList);
            }
            if(newBestFitness < bestFitness){
                bestSolution = newBestSolution.clone();
                bestFitness = newBestFitness;
            }
            currentSolution = newBestSolution.clone();
        }
        return bestSolution;
    }

    private void tabouListAdd(IOperation iOperation, Queue<IOperation> tabouList){
        if(tabouList.contains(iOperation))
            return;
        if(tabouList.size() >= tabouSize)
            tabouList.poll();
        tabouList.add(iOperation);
    }

    @Override
    public String toString() {
        return "TabuSearch,"+mapping+
                ",maxSteps,"+maxSteps+
                ",tabouSize,"+tabouSize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                "TabuSearch",
                mapping,
                maxSteps,
                tabouSize
        );
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }
}
