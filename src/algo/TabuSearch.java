package algo;

import algo.mapping.MPermutation;
import algo.operation.IOperation;
import algo.operation.ONoChangeOperation;
import javafx.util.Pair;
import utils.Landscape;
import utils.Order;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TabuSearch implements IAlgo {

    private final int maxSteps;
    private final int tabouSize;
    private MPermutation mapping = new MPermutation();

    public TabuSearch(int maxSteps, final int tabouSize){
        this.maxSteps = maxSteps;
        this.tabouSize = tabouSize;
    }

    @Override
    public Order compute(Landscape landscape) {
        return compute(landscape, new Order(landscape.getSIZE()).shuffle());
    }

    public Order compute(Landscape landscape, Order initialSolution){
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
}
