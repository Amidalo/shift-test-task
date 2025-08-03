package wild.yellow;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public record Statistics(Option option) {

    public void printStat(List<BigInteger> integers, List<BigDecimal> doubles, List<String> strings) {
        if (!integers.isEmpty()) {
            System.out.println("Статистика для целых чисел:\n" +
                    "Количество элементов записанных в исходный файл: " + integers.size());

            if (option.isFullStat()) {
                BigInteger min = integers.stream()
                        .min(BigInteger::compareTo)
                        .orElse(BigInteger.valueOf(0));

                BigInteger max = integers.stream()
                        .max(BigInteger::compareTo)
                        .orElse(BigInteger.valueOf(0));

                BigInteger sum = BigInteger.valueOf(integers.stream()
                        .mapToInt(BigInteger::intValue)
                        .sum());

                BigDecimal average = BigDecimal.valueOf(integers.stream()
                        .mapToDouble(BigInteger::doubleValue)
                        .average()
                        .orElse(0.0));

                System.out.printf("Минимум: %s\nМаксимум: %s\nСумма: %s\nСреднее: %s\n", min, max, sum, average);

            }
        }

        if (!doubles.isEmpty()) {
            System.out.println("\nСтатистика для вещественных чисел:\n" +
                    "Количество элементов записанных в исходный файл: " + doubles.size());

            if (option.isFullStat()) {
                BigDecimal min = doubles.stream()
                        .min(BigDecimal::compareTo)
                        .orElse(BigDecimal.valueOf(0.0));

                BigDecimal max = doubles.stream()
                        .max(BigDecimal::compareTo)
                        .orElse(BigDecimal.valueOf(0.0));

                BigDecimal sum = BigDecimal.valueOf(doubles.stream()
                        .mapToDouble(BigDecimal::doubleValue)
                        .sum());

                BigDecimal average = BigDecimal.valueOf(doubles.stream()
                        .mapToDouble(BigDecimal::doubleValue)
                        .average()
                        .orElse(0.0));

                System.out.printf("Минимум: %s\nМаксимум: %s\nСумма: %s\nСреднее: %s\n",
                        min, max, sum, average);

            }
        }

        if (!strings.isEmpty()) {
            System.out.println("\nСтатистика для строк:\n" +
                    "Количество элементов записанных в исходный файл: " + strings.size());

            if (option.isFullStat()) {
                int sizeOfShortString = strings.stream()
                        .mapToInt(String::length)
                        .min()
                        .orElse(0);

                int sizeOfLongString = strings.stream()
                        .mapToInt(String::length)
                        .max()
                        .orElse(0);

                System.out.printf("Размер короткой строки: %d\nРазмер длинной строки: %d\n",
                        sizeOfShortString, sizeOfLongString);

            }
        }
    }
}
