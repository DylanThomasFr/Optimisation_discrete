package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {

    public static void createBoard(String url) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(url)));
        String[] parts = content.split("\r\n",3);

        Integer size = Integer.parseInt(parts[0]);
        Integer[][] board = new Integer[size][size];
        for(String line : parts[2].split("\r\n")){
            for(String si : line.split(" ")){
                if(si.isEmpty())
                    continue;
                board[]
            }
        }
    }
}
