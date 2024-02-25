package cz.tomas.matikaapi;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public class TestUtil {

    public String getFileAsString(String filePath){
        try {
            Path path = Paths.get(filePath);
            StringBuilder builder = new StringBuilder();
            try (Stream<String> stream = Files.lines(path)){
                stream.forEach(builder::append);
            } catch (IOException e) {
                log.error("File could not be opened...are you sure the path '{}' really exists?", path);
            }
            return builder.toString();
        } catch (InvalidPathException invalidPathException){
            log.error("Path {} cannot be used to read a file.", filePath);
            return null;
        }
    }
}
