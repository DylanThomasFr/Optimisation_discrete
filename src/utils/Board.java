package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Board {

    private final Integer SIZE;
    private final Integer[][] distance;
    private final Integer[][] weights;

    public Board(Integer size, Integer[][] weights, Integer[][] distance){
        this.SIZE = size;
        this.weights = weights;
        this.distance = distance;
    }

    public Integer getSIZE(){
        return SIZE;
    }

    public Integer getD(Integer a, Integer b){
        return distance[a][b];
    }

    public Integer getW(Integer a, Integer b){
        return weights[a][b];
    }

    public Integer computeSum(LinkedList<Integer> order){
        int sum = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = i; j < SIZE; j++) {
                sum += weights[i][j]*distance[order.get(i)][order.get(j)];
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("- Board -\r\n")
                .append("size : ").append(SIZE).append("\r\n")
                .append("Distance : \r\n");
        for (Integer[] integers : distance) {
            for (Integer integer : integers) {
                stringBuilder.append(integer).append(" ");
            }
            stringBuilder.append("\r\n");
        }
        stringBuilder.append("\r\nWeights : \r\n");
        for (Integer[] integers : weights) {
            for (Integer integer : integers) {
                stringBuilder.append(integer).append(" ");
            }
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }
}
