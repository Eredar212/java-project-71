package hexlet.code.formatters;

import java.util.Map;

public class Stylish {
    public static <T> String getFormattedDiff(Map<String, Map<String, T>> diff) {
        StringBuilder sp = new StringBuilder("{\n");
        for (String key : diff.keySet()) {
            sp.append(formattedKeyDiff(key, diff.get(key)));
        }
        sp.append("}");
        return sp.toString();
    }

    private static <T> String formattedKeyDiff(String key, Map<String, T> map) {
        StringBuilder sp = new StringBuilder();
        for (String action : map.keySet()) {
            switch (action) {
                case "added":
                    sp.append(" ".repeat(2)).append("+ ").append(key).append(": ").append(map.get(action));
                    break;
                case "removed":
                    sp.append(" ".repeat(2)).append("- ").append(key).append(": ").append(map.get(action));
                    break;
                case "unchanged":
                    sp.append(" ".repeat(4)).append(key).append(": ").append(map.get(action));
                    break;
                default:
                    throw new RuntimeException("Illegal difference action");
            }
            sp.append("\n");
        }
        return sp.toString();
    }
}