package hexlet.code;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class App implements Callable<Integer> {
    @CommandLine.Parameters(index = "0", paramLabel = "filepath1", description = "path to first file.")
    private String filePath1;
    @CommandLine.Parameters(index = "1", paramLabel = "filepath2", description = "path to second file.")
    private String filePath2;
    @CommandLine.Option(names = {"-f", "--format"}, paramLabel = "format", defaultValue = "stylish",
            description = "output format [default: stylish]")
    private String format;

    @Override
    public Integer call() throws Exception {
        String diff = Differ.generate(filePath1, filePath2, format);
        System.out.println(diff);
        return 0;
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}
