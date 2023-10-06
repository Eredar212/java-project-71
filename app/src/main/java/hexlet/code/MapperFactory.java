package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

public class MapperFactory {
    public static ObjectMapper createMapper(FileExt ext) {
        return switch (ext) {
            case JSON -> new ObjectMapper();
            case YML -> new YAMLMapper();
            default -> throw new RuntimeException("Unsupported file extension");
        };
    }
}
