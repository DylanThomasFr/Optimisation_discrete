package algo.mapping;

import algo.operation.IOperation;
import utils.Order;

import java.util.List;

public interface IMapping {

    List<Order> getNeighbors(final Order order);

    List<IOperation> getOperations(final Order order);
}
