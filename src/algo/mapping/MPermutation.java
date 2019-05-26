package algo.mapping;

import algo.operation.IOperation;
import algo.operation.OPermutation;
import utils.Order;

import java.util.ArrayList;
import java.util.List;

public class MPermutation implements IMapping{

    @Override
    public List<Order> getNeighbors(final Order order) {
        ArrayList<Order> neighbors=new ArrayList<>();
        for(int i=0; i<order.size(); i++){
            for(int j=i+1; j<order.size(); j++){
                OPermutation oPermutation = new OPermutation(i,j);
                neighbors.add(oPermutation.applyOperation(order));
            }
        }
        return neighbors;
    }

    @Override
    public List<IOperation> getOperations(final Order order) {
        ArrayList<IOperation> operations =new ArrayList<>();
        for(int i=0; i<order.size(); i++){
            for(int j=i+1; j<order.size(); j++){
                operations.add(new OPermutation(i,j));
            }
        }
        return operations;
    }
}
