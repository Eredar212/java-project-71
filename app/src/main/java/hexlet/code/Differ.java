package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        format = format.isEmpty() ? "stylish" : format;
        ObjectMapper objectMapper = new ObjectMapper();
        // Формируем абсолютный путь,
        // если filePath будет содержать относительный путь,
        // то мы всегда будет работать с абсолютным
        Path path1 = Paths.get(filePath1).toAbsolutePath().normalize();
        Path path2 = Paths.get(filePath2).toAbsolutePath().normalize();
        // Проверяем существование файла
        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }
        if (!Files.exists(path2)) {
            throw new Exception("File '" + path2 + "' does not exist");
        }
        // Читаем файл
        String content1 = Files.readString(path1);
        String content2 = Files.readString(path2);
        Map<String, Object> map1 = objectMapper.readValue(content1, new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> map2 = objectMapper.readValue(content2, new TypeReference<Map<String, Object>>() {
        });
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
