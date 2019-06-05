package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BenchResultWriter {

    private final String folderpath;
    private final String algo;
    private final String landscape;

    private final Path path;

    public BenchResultWriter(
            final String folderpath,
            final String algo,
            final String landscape
            ){
        this.folderpath = folderpath;
        this.algo = algo;
        this.landscape = landscape;

        path = Paths.get(folderpath + "/" + algo + "_" + landscape + ".txt");
    }

    public BenchResultWriter write(List<String> lines){
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            for (String line : lines) {
                writer.write(line + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
