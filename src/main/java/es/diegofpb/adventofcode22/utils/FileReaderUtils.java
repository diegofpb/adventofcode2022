package es.diegofpb.adventofcode22.utils;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@UtilityClass
public class FileReaderUtils {

    public List<String> readInputFile(@NonNull final String fileName) {
        Path path = Paths.get("src/main/resources/", fileName, "/input");
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
