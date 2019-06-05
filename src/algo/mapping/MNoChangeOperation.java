package algo.mapping;

import algo.operation.IOperation;
import algo.operation.ONoChangeOperation;
import javafx.util.Pair;
import utils.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MNoChangeOperation implements IMapping {

    @Override
    public List<Order> getNeighbors(Order order) {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        return orders;
    }

    @Override
    public List<Pair<Order, IOperation>> getNeighborsOperations(Order order) {
        List<Pair<Order, IOperation>> pairs = new ArrayList<>();
        pairs.add(new Pair<>(order, new ONoChangeOperation()));
        return pairs;
    }

    @Override
    public List<Order> getNeighborsExcept(Order order, List<IOperation> exceptOperations) {
        return getNeighbors(order);
    }

    @Override
    public List<Pair<Order, IOperation>> getNeighborsExceptOperations(Order order, List<IOperation> exceptOperations) {
        return getNeighborsOperations(order);
    }

    @Override
    public String toString() {
        return "MNoChangeOperation";
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
