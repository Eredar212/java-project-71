package hexlet.code;

import com.fasterxml.jackson.databind.ObjectMapper;

import hexlet.code.Utils.Difference;
import hexlet.code.Utils.FileExt;
import hexlet.code.Utils.Formatter;
import hexlet.code.Utils.MapperFactory;
import hexlet.code.Utils.Parser;
import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        FileExt extension = FileExt.valueOf(filePath1.substring(filePath1.lastIndexOf(".") + 1).toUpperCase());
        ObjectMapper objectMapper = MapperFactory.createMapper(extension);
        Map<String, Object> map1 = Parser.parse(filePath1, objectMapper);
        Map<String, Object> map2 = Parser.parse(filePath2, objectMapper);
        Map<String, Map<String, Object>> diff = Difference.getDiff(map1, map2);
        return Formatter.getFormattedDiffString(diff, format);
    }
    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }
}
