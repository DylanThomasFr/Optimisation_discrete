package algo.mapping;

import algo.operation.IOperation;
import algo.operation.OPermutation;
import javafx.util.Pair;
import utils.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MSwapping implements IMapping {

    @Override
    public List<Order> getNeighbors(Order order) {return getNeighborsExcept(order, new ArrayList<>());
    }

    @Override
    public List<Pair<Order, IOperation>> getNeighborsOperations(Order order) {
        return getNeighborsExceptOperations(order, new ArrayList<>());
    }

    @Override
    public List<Order> getNeighborsExcept(Order order, List<IOperation> exceptOperations) {
        ArrayList<Order> neighbors=new ArrayList<>();
        for(int i=0; i<order.size()-1; i++){
            OPermutation oPermutation = new OPermutation(i,i+1);
            if(exceptOperations.contains(oPermutation))
                continue;
            neighbors.add(oPermutation.applyOperation(order));
        }
        return neighbors;
    }

    @Override
    public List<Pair<Order, IOperation>> getNeighborsExceptOperations(Order order, List<IOperation> exceptOperations) {
        List<Pair<Order, IOperation>> neighbors = new ArrayList<>();
        for(int i=0; i<order.size()-1; i++){
            OPermutation oPermutation = new OPermutation(i,i+1);
            if(
                    exceptOperations.contains(oPermutation) ||
                            exceptOperations.contains(oPermutation.getReverseOperation())){
                continue;
            }
            neighbors.add(new Pair<>(oPermutation.applyOperation(order), oPermutation));
        }
        return neighbors;
    }

    @Override
    public String toString() {
        return "MSwapping";
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                toString()
        );
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }
}
