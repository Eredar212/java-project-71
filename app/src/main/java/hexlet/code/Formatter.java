package hexlet.code;

import hexlet.code.formatters.Plain;
import hexlet.code.formatters.Stylish;
import java.util.Map;

public class Formatter {

    public static String getFormattedDiffString(Map<String, Map<String, Object>> diff, String format) {
        return switch (format) {
            case "stylish" -> Stylish.getFormattedDiff(diff);
            case "plain" -> Plain.getFormattedDiff(diff);
            default -> throw new RuntimeException("Unsupported diff format");
        };
    }
}
