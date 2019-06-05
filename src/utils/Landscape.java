package utils;

import java.util.Objects;

public class Landscape {

    private final Integer SIZE;
    private final Integer[][] distance;
    private final Integer[][] weights;

    public Landscape(Integer size, Integer[][] weights, Integer[][] distance){
        this.SIZE = size;
        this.weights = weights.clone();
        this.distance = distance.clone();
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

    public long computeFitness(Order order){
        long sum = 0L;
        for (int i = 0; i < SIZE; i++) {
            for (int j = i; j < SIZE; j++) {
                sum += weights[i][j]*distance[order.get(i)][order.get(j)];
            }
        }
        return 2*sum;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("- Landscape -\r\n")
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

    @Override
    public int hashCode() {
        return Objects.hash(
                SIZE,
                weights,
                distance
        );
    }

    @Override
    public boolean equals(Object obj) {
        return obj.hashCode() == this.hashCode();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Landscape(SIZE, weights, distance);
    }
}
