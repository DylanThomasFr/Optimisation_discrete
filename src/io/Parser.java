package io;

import utils.Landscape;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Parser {

    public static Landscape createLandscape(String url) throws Exception {
        String content = clearSpaces(
                new String(Files.readAllBytes(Paths.get(url)))
        );
        String part1 = "";
        String part2 = "";
        int size = 0;

        String[] parts =  content.replaceAll("\r\n\r\n", "\r\n").split("\r\n");
        size = Integer.parseInt(parts[0].replaceAll(" ", ""));
        for (int i = 1; i < size + 1; i++) {
            part1 += parts[i] + "\r\n";
        }
        for (int i = 1 + size; i < 2 * size + 1; i++) {
            part2 += parts[i] + "\r\n";
        }
        return new Landscape(
                url,
                size,
                formatArray(size, part1),
                formatArray(size, part2)
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
