package hexlet.code;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DiffBuilder {
    public static Map<String, Map<String, Object[]>> getDiff(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(map2.keySet());
        Map<String, Map<String, Object[]>> diff = new LinkedHashMap<>(); //важен порядок считывания
        for (String key : keySet) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (!Objects.equals(map1.get(key), map2.get(key))) {
                    diff.put(key, Map.of("updated", new Object[]{map2.get(key), map1.get(key)}));
                } else {
                    diff.put(key, Map.of("unchanged", new Object[]{map1.get(key)}));
                }
            } else {
                if (map1.containsKey(key)) {
                    diff.put(key, Map.of("removed", new Object[]{map1.get(key)}));
                } else {
                    diff.put(key, Map.of("added", new Object[]{map2.get(key)}));
                }
            }
        }
        return diff;
    }
}
