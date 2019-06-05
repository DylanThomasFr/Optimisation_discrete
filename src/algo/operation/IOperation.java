package algo.operation;

import utils.Order;

public interface IOperation {

    /**
     * @param order
     * @return Return a new Order with the operation applyed.
     */
    Order applyOperation(Order order);

    /**
     * @return The operation to reverse the one already applyed
     */
    IOperation getReverseOperation();

    int hashCode();

    boolean equals(Object obj);

    String toString();
}
