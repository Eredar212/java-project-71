package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import java.util.Map;

public class JsonParser implements Parser {
    public Map<String, Object> parse(String content) throws JsonProcessingException {
        ObjectReader reader = new ObjectMapper().readerFor(Map.class);
        return reader.readValue(content);
    }
}
