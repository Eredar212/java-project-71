package hexlet.code.Utils;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Difference {
    public static <T> Map<String, Map<String, T>> getDiff(Map<String, T> map1, Map<String, T> map2) {
        Set<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(map2.keySet());
        Map<String, Map<String, T>> diff = new LinkedHashMap<>(); //важен порядок считывания
        for (String key : keySet) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (!Objects.equals(map1.get(key), map2.get(key))) {
                    //важен порядок считывания, поэтому используем LinkedHasMap
                    Map<String, T> linkedMap = new LinkedHashMap<>();
                    linkedMap.put("removed", map1.get(key));
                    linkedMap.put("added", map2.get(key));
                    diff.put(key, linkedMap);
                } else {
                    diff.put(key, Map.of("unchanged", map1.get(key)));
                }
            } else {
                if (map1.containsKey(key)) {
                    diff.put(key, Map.of("removed", map1.get(key)));
                } else {
                    diff.put(key, Map.of("added", map2.get(key)));
                }
            }
        }
        return diff;
    }
}
