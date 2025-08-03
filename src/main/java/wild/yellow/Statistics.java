package wild.yellow;

import java.util.List;

public record Statistics(Option option) {

    public void printStat(List<Integer> integers, List<Double> doubles, List<String> strings) {
        if (!integers.isEmpty()) {
            System.out.println("Статистика для целых чисел:\n" +
                    "Количество элементов записанных в исходный файл: " + integers.size());

            if (option.isFullStat()) {
                int min = integers.stream()
                        .min(Integer::compare)
                        .orElse(0);

                int max = integers.stream()
                        .max(Integer::compare)
                        .orElse(0);

                int sum = integers.stream()
                        .mapToInt(Integer::intValue)
                        .sum();

                double average = integers.stream()
                        .mapToDouble(Integer::doubleValue)
                        .average()
                        .orElse(0.0);

                System.out.printf("Минимум: %d\nМаксимум: %d\nСумма: %d\nСреднее: %.2f\n", min, max, sum, average);

            }
        }

        if (!doubles.isEmpty()) {
            System.out.println("\nСтатистика для вещественных чисел:\n" +
                    "Количество элементов записанных в исходный файл: " + doubles.size());

            if (option.isFullStat()) {
                double min = doubles.stream()
                        .min(Double::compare)
                        .orElse(0.0);

                double max = doubles.stream()
                        .max(Double::compare)
                        .orElse(0.0);

                double sum = doubles.stream()
                        .mapToDouble(Double::doubleValue)
                        .sum();

                double average = doubles.stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(0.0);

                System.out.printf("Минимум: %.2f\nМаксимум: %.2f\nСумма: %.2f\nСреднее: %.2f\n",
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
