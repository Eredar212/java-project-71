package hexlet.code.formatters;

import java.util.Map;

import static hexlet.code.formatters.Plain.NEW_VALUE_INDEX;
import static hexlet.code.formatters.Plain.OLD_VALUE_INDEX;

public class Stylish {
    private static final int OFFSET = 4;
    private static final int MIN_OFFSET = 2;
    public static String getFormattedDiff(Map<String, Map<String, Object[]>> diff) {
        StringBuilder sp = new StringBuilder("{\n");
        for (String key : diff.keySet()) {
            sp.append(formattedKeyDiff(key, diff.get(key)));
        }
        sp.append("}");
        return sp.toString();
    }

    private static <T> String formattedKeyDiff(String key, Map<String, Object[]> map) {
        StringBuilder sp = new StringBuilder();
        for (String action : map.keySet()) {
            switch (action) {
                case "updated":
                    sp.append(" ".repeat(MIN_OFFSET)).append("- ").append(key).append(": ")
                            .append(map.get(action)[OLD_VALUE_INDEX]).append("\n");
                    sp.append(" ".repeat(MIN_OFFSET)).append("+ ").append(key).append(": ")
                            .append(map.get(action)[NEW_VALUE_INDEX]);
                    break;
                case "added":
                    sp.append(" ".repeat(MIN_OFFSET)).append("+ ").append(key).append(": ")
                            .append(map.get(action)[NEW_VALUE_INDEX]);
                    break;
                case "removed":
                    sp.append(" ".repeat(MIN_OFFSET)).append("- ").append(key).append(": ").
                            append(map.get(action)[NEW_VALUE_INDEX]);
                    break;
                case "unchanged":
                    sp.append(" ".repeat(OFFSET)).append(key).append(": ").append(map.get(action)[NEW_VALUE_INDEX]);
                    break;
                default:
                    throw new RuntimeException("Illegal difference action: " + action);
            }
            sp.append("\n");
        }
        return sp.toString();
    }
}
