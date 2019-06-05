package algo.operation;

import utils.Order;

import java.util.Objects;

public class ONoChangeOperation implements IOperation {

    public ONoChangeOperation(){}

    @Override
    public Order applyOperation(Order order) {
        return order;
    }

    @Override
    public IOperation getReverseOperation() {
        return new ONoChangeOperation();
    }

    @Override
    public int hashCode() {
        return Objects.hash("ONoChangeOperation");
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new ONoChangeOperation();
    }

    @Override
    public String toString() {
        return "NoChangeOperation";
    }
}
