package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import hexlet.code.Parser;
import java.util.Map;

public final class YamlParser implements Parser {
    @Override
    public Map<String, Object> parse(String content) throws JsonProcessingException {
        ObjectReader reader = new YAMLMapper().readerFor(Map.class);
        return reader.readValue(content);
    }
}
