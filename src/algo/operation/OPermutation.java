package algo.operation;

import utils.Order;

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
}
