import io.Parser;
import utils.Board;

public class Main {

    public static void main(String[] args) {
        try {
            Board board = Parser.createBoard("data/tai12a.dat");
            System.out.println("board = " + board);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
