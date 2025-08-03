package wild.yellow;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public record FileWriter(Option option) {

    public void writeToFile(List<Integer> integers, List<Double> doubles, List<String> strings) throws IOException {
        if (!integers.isEmpty()) {
            Path path = option.getResultPath().resolve(option.getPrefix() + "integers.txt");
            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(path.toFile(), option.isAddedToAnExistingFile()))) {
                for (Integer it : integers) {
                    writer.write(String.valueOf(it));
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Ошибка записи: " + e.getMessage());
            }
        }

        if (!doubles.isEmpty()) {
            Path path = option.getResultPath().resolve(option.getPrefix() + "doubles.txt");
            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(path.toFile(), option.isAddedToAnExistingFile()))) {
                for (Double it : doubles) {
                    writer.write(String.valueOf(it));
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Ошибка записи: " + e.getMessage());
            }
        }

        if (!strings.isEmpty()) {
            Path path = option.getResultPath().resolve(option.getPrefix() + "strings.txt");
            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(path.toFile(), option.isAddedToAnExistingFile()))) {
                for (String it : strings) {
                    writer.write(it);
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Ошибка записи: " + e.getMessage());
            }
        }
    }
}
