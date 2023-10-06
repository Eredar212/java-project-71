package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        format =  format == null  || format.isEmpty() ? "stylish" : format;
        FileExt extension = FileExt.valueOf(filePath1.substring(filePath1.lastIndexOf(".") + 1).toUpperCase());
        ObjectMapper objectMapper = MapperFactory.createMapper(extension);
        Map<String, Object> map1 = Parser.parse(filePath1, objectMapper);
        Map<String, Object> map2 = Parser.parse(filePath2, objectMapper);
        /*System.out.println("map1");
        System.out.println(map1);
        System.out.println("map2");
        System.out.println(map2);*/
        return getDiff(map1, map2);
    }

    private static <T> String getDiff(Map<String, T> map1, Map<String, T> map2) {
        StringBuilder sp = new StringBuilder();
        Set<String> keySet = new TreeSet<>(map1.keySet());
        keySet.addAll(map2.keySet());
        for (String key : keySet) {
            if (map1.containsKey(key) && map2.containsKey(key)) {
                if (!map1.get(key).equals(map2.get(key))) {
                    sp.append("- ").append(key).append(": ").append(map1.get(key)).append("\n");
                    sp.append("+ ").append(key).append(": ").append(map2.get(key));
                } else {
                    sp.append("  ").append(key).append(": ").append(map1.get(key));
                }
            } else {
                if (map1.containsKey(key)) {
                    sp.append("- ").append(key).append(": ").append(map1.get(key));
                } else {
                    sp.append("+ ").append(key).append(": ").append(map2.get(key));
                }
            }
            sp.append("\n");
        }
        sp.delete(sp.lastIndexOf("\n"), sp.length());
        return sp.toString();
    }
}
