import wild.yellow.FileReader;
import wild.yellow.FileWriter;
import wild.yellow.Option;
import wild.yellow.Statistics;

public class Main {
    public static void main(String[] args) {

        try {
            Option option = Option.extract(args);

            FileReader fileReader = new FileReader(option);
            fileReader.readFiles(option.getInputFiles());

            FileWriter fileWriter = new FileWriter(option);
            fileWriter.writeToFile
                    (fileReader.getIntegers(), fileReader.getDoubles(), fileReader.getStrings());

            Statistics statistics = new Statistics(option);
            statistics.printStat
                    (fileReader.getIntegers(), fileReader.getDoubles(), fileReader.getStrings());

        } catch (Exception e) {
            System.err.println("Ошибка: " + e.getMessage());
        }
    }
}
