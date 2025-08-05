package wild.yellow;

import java.io.BufferedWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public record FileWriter(Option option) {

    public void writeToFile(List<BigInteger> integers, List<BigDecimal> floats, List<String> strings) throws IOException {
        if (!integers.isEmpty()) {
            Path path = option.getResultPath().resolve(option.getPrefix() + "integers.txt");
            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(path.toFile(), option.isAddedToAnExistingFile()))) {
                for (BigInteger it : integers) {
                    writer.write(String.valueOf(it));
                    writer.newLine();
                }
            } catch (IOException e) {
                System.err.println("Ошибка записи: " + e.getMessage());
            }
        }

        if (!floats.isEmpty()) {
            Path path = option.getResultPath().resolve(option.getPrefix() + "floats.txt");
            Files.createDirectories(path.getParent());

            try (BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(path.toFile(), option.isAddedToAnExistingFile()))) {
                for (BigDecimal it : floats) {
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
