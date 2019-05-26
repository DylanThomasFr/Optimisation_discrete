import io.Parser;
import utils.Board;
import utils.Order;

public class Main {

    public static void main(String[] args) throws Exception {
        Board board = Parser.createBoard("data/tai12a.dat");

        Order order = new Order("8,1,6,2,11,10,3,5,9,7,12,4");

        int best = board.computeFitness(order);
        System.out.println("board.computeFitness() = " + best);
//        int i = 1000000;
//        int newValue;
//        while(i-- > 0){
//            Collections.shuffle(order);
//            newValue = board.computeFitness(order);
//            if(newValue < best)
//                System.out.println("newValue = " + newValue);
////            System.out.println("board.computeFitness() = " + newValue + " " + (newValue < best));
//        }
    }
}
