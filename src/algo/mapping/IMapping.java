package algo.mapping;

import algo.operation.IOperation;
import javafx.util.Pair;
import utils.Order;

import java.util.List;

public interface IMapping {

    List<Order> getNeighbors(final Order order);

    List<Pair<Order, IOperation>> getNeighborsOperations(final Order order);

    List<Order> getNeighborsExcept(final Order order, final List<IOperation> exceptOperations);

    List<Pair<Order, IOperation>> getNeighborsExceptOperations(final Order order, final List<IOperation> exceptOperations);

//    List<IOperation> getOperations(final Order order);
}
