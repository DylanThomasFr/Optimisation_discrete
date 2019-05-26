package utils;

import java.util.Arrays;

public class Order {

    private final int SIZE;
    private final Integer[] orders;

    public Order(String solutions){
        String[] orderL = solutions.split(",");
        SIZE = orderL.length;
        orders = new Integer[SIZE];
        for (int i = 0; i < orderL.length; i++) {
            orders[i] = Integer.parseInt(orderL[i])-1;
        }
    }

    public Order(int SIZE){
        this.SIZE = SIZE;
        orders = new Integer[SIZE];
        for (int i = 0; i < SIZE; i++) {
            orders[i] = i;
        }
    }

    public Integer get(Integer i){
        return orders[i];
    }

    @Override
    public String toString() {
        return Arrays.toString(orders);
    }
}
