package wild.yellow;

import lombok.Getter;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class Option {

    private final boolean isAddedToAnExistingFile;
    private final boolean isFullStat;
    private final Path resultPath;
    private final String prefix;
    List<String> inputFiles;

    public Option(boolean isAddedToAnExistingFile, boolean isFullStat, String resultPath, String prefix,
                  List<String> inputFiles) {

        this.isAddedToAnExistingFile = isAddedToAnExistingFile;
        this.isFullStat = isFullStat;
        this.resultPath = Path.of(resultPath == null ? "." : resultPath);
        this.prefix = prefix == null ? "" : prefix;
        this.inputFiles = inputFiles;
    }

    public static Option extract(String[] args) {

        boolean isAddedToAnExistingFile = false;
        boolean isFullStat = false;
        Boolean isOptionStatOn = null;
        String resultPath = null;
        String prefix = null;

        List<String> inputFiles = Arrays.stream(args)
                .filter(arg -> arg.contains("txt"))
                .collect(Collectors.toCollection(ArrayList::new));

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-a" -> isAddedToAnExistingFile = true;
                case "-o" -> {
                    if (i + 1 < args.length && !args[i].startsWith("-")) resultPath = args[++i];
                    else throw new IllegalArgumentException("Не указан путь для сохранения результатов!");
                }
                case "-p" -> {
                    if (i + 1 < args.length && !args[i].startsWith("-")) prefix = args[++i];
                    else throw new IllegalArgumentException("Не указан префикс для выходных файлов!");
                }
                case "-s" ->  {
                    if (isOptionStatOn != null)
                        throw new IllegalArgumentException
                                ("Запрещено указывать опции -s и -f вместе, также запрещено повторять эти опции!");
                    isFullStat = false;
                    isOptionStatOn = false;
                }
                case "-f" -> {
                    if (isOptionStatOn != null)
                        throw new IllegalArgumentException
                                ("Запрещено указывать опции -s и -f вместе, также запрещено повторять эти опции!");
                    isFullStat = true;
                    isOptionStatOn = true;
                }
                default -> {
                    if (args[i].startsWith("-"))
                        throw new IllegalArgumentException("Неизвестная опция: " + args[i]);
                }
            }
        }

        if (inputFiles.isEmpty())
            throw new IllegalArgumentException("Вы не указали входные файлы. Укажите минимум 1 входной файл!");

        return new Option(isAddedToAnExistingFile, isFullStat, resultPath, prefix, inputFiles);
    }
}
