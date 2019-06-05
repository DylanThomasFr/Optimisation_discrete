package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BenchResultWriter {

    private final String folderpath;
    private final Path path;
    private final String filename;

    public BenchResultWriter(
            final String folderpath,
            final String filename
            ){
        this.folderpath = folderpath;

        path = Paths.get(folderpath + "/" + filename + ".csv");
        this.filename = filename;
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
