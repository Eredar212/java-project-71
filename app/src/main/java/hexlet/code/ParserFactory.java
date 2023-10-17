package hexlet.code;

import hexlet.code.parsers.JsonParser;
import hexlet.code.parsers.YamlParser;

public class ParserFactory {
    public static Parser createParser(String type) {
        return switch (DataType.valueOf(type)) {
            case JSON -> new JsonParser();
            case YML, YAML -> new YamlParser();
            default -> throw new RuntimeException("Unsupported data type: " + type);
        };
    }
}
