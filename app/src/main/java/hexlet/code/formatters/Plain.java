package hexlet.code.formatters;

import java.util.Map;

public class Plain {
    protected static final int NEW_VALUE_INDEX = 0;
    protected static final int OLD_VALUE_INDEX = 1;
    public  static String getFormattedDiff(Map<String, Map<String, Object[]>> diff) {
        StringBuilder sp = new StringBuilder();
        for (String key: diff.keySet()) {
            sp.append(formattedKeyDiff(key, diff.get(key)));
        }
        return sp.delete(sp.lastIndexOf("\n"), sp.length()).toString();
    }
    private static String formattedKeyDiff(String key, Map<String, Object[]> map) {
        StringBuilder sp = new StringBuilder();
        for (String action: map.keySet()) {
            switch (action) {
                case "updated":
                    Object[] updatedData = map.get("updated");
                    sp.append("Property '").append(key).append("' was updated. From ")
                            .append(getPlainValue(updatedData[OLD_VALUE_INDEX]))
                            .append(" to ").append(getPlainValue(updatedData[NEW_VALUE_INDEX]))
                            .append("\n");
                    break;
                case "removed":
                    sp.append("Property '").append(key).append("' was removed").append("\n");
                    break;
                case "added":
                    sp.append("Property '").append(key).append("' was added with value: ")
                            .append(getPlainValue(map.get(action)[NEW_VALUE_INDEX])).append("\n");
                    break;
                case "unchanged":
                    //sp.append(" ".repeat(4)).append(key).append(": ").append(map.get(action));
                    break;
                default:
                    throw new RuntimeException("Illegal difference action: " + action);
            }
        }
        return sp.toString();
    }
    private static String getPlainValue(Object o) {
        if (o == null) {
            return "null";
        } else if (o instanceof String) {
            return "'" + o + "'";
        } else if (o instanceof Integer || o instanceof Boolean) {
            return o.toString();
        } else {
            return "[complex value]";
        }
    }
}
