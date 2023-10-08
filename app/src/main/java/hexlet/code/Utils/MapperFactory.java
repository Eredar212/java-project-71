package hexlet.code.Utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.Utils.FileExt;

public class MapperFactory {
    public static ObjectMapper createMapper(FileExt ext) {
        return switch (ext) {
            case JSON -> new ObjectMapper();
            case YML -> new YAMLMapper();
            default -> throw new RuntimeException("Unsupported file extension");
        };
    }
}
