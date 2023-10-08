package hexlet.code.Utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    public static Map<String, Object> parse(String filePath, ObjectMapper objectMapper) throws Exception {
        Path path1 = Paths.get(filePath).toAbsolutePath().normalize();
        // Проверяем существование файла
        if (!Files.exists(path1)) {
            throw new Exception("File '" + path1 + "' does not exist");
        }
        // Читаем файл
        String content1 = Files.readString(path1);
        /*System.out.println("content of " + filePath);
        System.out.println(content1);*/
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        return objectMapper.readValue(content1, typeRef);
    }
}
