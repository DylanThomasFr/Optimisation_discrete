package io;

import utils.Board;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {

    public static Board createBoard(String url) throws Exception {
        String content = clearSpaces(
                new String(Files.readAllBytes(Paths.get(url)))
        );
        String[] parts = content.split("\r\n\r\n");
        if(parts.length < 3)
            throw new Exception("Bad format file");
        Integer size = Integer.parseInt(parts[0].replaceAll(" ", ""));
        return new Board(
                size,
                formatArray(size, parts[1]),
                formatArray(size, parts[2])
        );
    }

    private static Integer[][] formatArray(Integer size, String s){
        Integer[][] array = new Integer[size][size];

        int i = 0;
        int j;
        for(String line : s.split("\r\n")){
            j=0;
            for(String si : line.trim().split(" ")){
                array[i][j] = Integer.parseInt(si);
                j++;
            }
            i++;
        }
        return array;
    }

    private static String clearSpaces(String s){
        String content_raw = s;
        String content_tmp;
        do {
            content_tmp = content_raw;
            content_raw = content_raw.replaceAll("  ", " ");
        } while(!content_tmp.equalsIgnoreCase(content_raw));
        return content_raw;
    }
}
