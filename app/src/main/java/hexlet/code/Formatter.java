package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import hexlet.code.formatters.Json;
import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.Map;

public class Formatter {
    public static String getFormattedDiffString(Map<String, Map<String, Object[]>> diff, String format)
            throws JsonProcessingException {

        return switch (format) {
            case "stylish", "" -> Stylish.getFormattedDiff(diff);
            case "plain" -> Plain.getFormattedDiff(diff);
            case "json" -> Json.getFormattedDiff(diff);
            default -> throw new RuntimeException("Unsupported \"format\" option: " + format);
        };
    }
}
