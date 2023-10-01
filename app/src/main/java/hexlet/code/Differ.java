package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
@Command(name = "gendiff", mixinStandardHelpOptions = true, version = "gendiff 0.1",
        description = "Compares two configuration files and shows a difference.")
public class Differ {
	@Parameters(index = "0", paramLabel = "filepath1", description = "path to first file.")
	private String filePath1;
	@Parameters(index = "1", paramLabel = "filepath2", description = "path to second file.")
	private String filePath2;
	@Option(names = {"-f", "--format"}, paramLabel = "format", description = "output format [default: stylish]")
	private String format = "stylish";
}
