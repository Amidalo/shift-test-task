package wild.yellow;

import lombok.Getter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
public class FileReader {

    private final List<Integer> integers = new ArrayList<>();
    private final List<Double> doubles = new ArrayList<>();
    private final List<String> strings = new ArrayList<>();

    Option option;

    public FileReader(Option option) {
        this.option = option;
    }

    public void readFiles(List<String> files) {
        for (String file: files) {
            try (BufferedReader reader = new BufferedReader(new java.io.FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (!line.trim().isEmpty()) {
                        DataTypeDefiner.DefinedType type = DataTypeDefiner.define(line.trim());
                        switch (type.type()) {
                            case INTEGER ->
                                    integers.add((Integer) type.value());
                            case DOUBLE ->
                                    doubles.add((Double) type.value());
                            case STRING ->
                                    strings.add((String) type.value());
                        }
                    }
                }
            } catch (IOException e) {
                System.err.println("Ошибка чтения файла: " + e.getMessage());
            }
        }
    }
}
