import io.Parser;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Parser.createBoard("data/tai12a.dat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
