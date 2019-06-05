package algo.mapping;

import algo.operation.IOperation;
import algo.operation.OPermutation;
import javafx.util.Pair;
import utils.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MPermutation implements IMapping{

    @Override
    public List<Order> getNeighbors(final Order order) {
        return getNeighborsExcept(order, new ArrayList<>());
    }

    @Override
    public List<Pair<Order, IOperation>> getNeighborsOperations(Order order) {
        return getNeighborsExceptOperations(order, new ArrayList<>());
    }

    @Override
    public List<Order> getNeighborsExcept(Order order, List<IOperation> exceptOperations) {
        ArrayList<Order> neighbors=new ArrayList<>();
        for(int i=0; i<order.size(); i++){
            for(int j=i+1; j<order.size(); j++){
                OPermutation oPermutation = new OPermutation(i,j);
                if(exceptOperations.contains(oPermutation))
                    continue;
                neighbors.add(oPermutation.applyOperation(order));
            }
        }
        return neighbors;
    }

    @Override
    public List<Pair<Order, IOperation>> getNeighborsExceptOperations(Order order, List<IOperation> exceptOperations) {
        List<Pair<Order, IOperation>> neighbors = new ArrayList<>();
        for(int i=0; i<order.size(); i++){
            for(int j=i+1; j<order.size(); j++){
                OPermutation oPermutation = new OPermutation(i,j);
                if(
                        exceptOperations.contains(oPermutation) ||
                        exceptOperations.contains(oPermutation.getReverseOperation())){
                    continue;
                }
                neighbors.add(new Pair<>(oPermutation.applyOperation(order), oPermutation));
            }
        }
        return neighbors;
    }

    @Override
    public String toString() {
        return "MPermutation";
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
