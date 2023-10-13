package hexlet.code;

public class ParserFactory {
    public static Parser createParser(String ext) {
        return switch (FileExt.valueOf(ext)) {
            case JSON -> new JsonParser();
            case YML -> new YamlParser();
            default -> throw new RuntimeException("Unsupported file extension");
        };
    }
}
