package hexlet.code.formatters;

import java.util.Map;

public class Plain {
    public  static <T> String getFormattedDiff(Map<String, Map<String, T>> diff) {
        StringBuilder sp = new StringBuilder();
        for (String key: diff.keySet()) {
            sp.append(formattedKeyDiff(key, diff.get(key)));
        }
        return sp.delete(sp.lastIndexOf("\n"), sp.length()).toString();
    }
    private static <T> String formattedKeyDiff(String key, Map<String, T> map) {
        if (map.size() == 2) {
            return "Property '" + key + "' was updated. From " + getPlainValue(map.get("removed")) + " to "
                    + getPlainValue(map.get("added")) + "\n";
        }
        StringBuilder sp = new StringBuilder();
        for (String action: map.keySet()) {
            switch (action) {
                case "removed":
                    sp.append("Property '").append(key).append("' was removed").append("\n");
                    break;
                case "added":
                    sp.append("Property '").append(key).append("' was added with value: ")
                            .append(getPlainValue(map.get(action))).append("\n");
                    break;
                case "unchanged":
                    //sp.append(" ".repeat(4)).append(key).append(": ").append(map.get(action));
                    break;
                default:
                    throw new RuntimeException("Illegal difference action");
            }
        }
        return sp.toString();
    }
    private static Object getPlainValue(Object o) {
        if (o == null) {
            return "null";
        } else if (o instanceof String) {
            return "'" + o + "'";
        } else if (o instanceof Integer || o instanceof Boolean) {
            return o;
        } else {
            return "[complex value]";
        }
    }
}
