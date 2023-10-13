package hexlet.code.parsers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import hexlet.code.Parser;
import java.util.Map;

public final class JsonParser implements Parser {
    public Map<String, Object> parse(String content) throws JsonProcessingException {
        ObjectReader reader = new ObjectMapper().readerFor(Map.class);
        return reader.readValue(content);
    }
}
