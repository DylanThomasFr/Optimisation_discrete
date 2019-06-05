package algo.operation;

import utils.Order;

import java.util.Arrays;
import java.util.Objects;

public class OPermutation implements IOperation {

    private final int a;
    private final int b;

    public OPermutation(int a, int b){
        this.a = a;
        this.b = b;
    }

    @Override
    public Order applyOperation(Order order) {
        Order newOrder = order.clone();
        return newOrder
                .set(a, order.get(b))
                .set(b, order.get(a));
    }

    @Override
    public IOperation getReverseOperation() {
        return new OPermutation(b, a);
    }

    @Override
    public String toString() {
        return String.format("Permutation [%d;%d]", a, b);
    }

    @Override
    public int hashCode() {
        if(a<b)
            return Objects.hash(
                    "Permutation",
                    a,
                    b
            );
        else
            return Objects.hash(
                    "Permutation",
                    b,
                    a
            );
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new OPermutation(a,b);
    }
}
