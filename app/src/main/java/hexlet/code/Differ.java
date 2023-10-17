package hexlet.code;

import java.util.Map;

public class Differ {
    public static String generate(String filePath1, String filePath2, String format) throws Exception {
        String content1 = Supplier.getData(filePath1);
        String content2 = Supplier.getData(filePath2);
        Parser parser = ParserFactory.createParser(getDataFormat(filePath1).toUpperCase());
        Map<String, Object> data1 = parser.parse(content1);
        Map<String, Object> data2 = parser.parse(content2);
        Map<String, Map<String, Object[]>> diff = DiffBuilder.getDiff(data1, data2);
        return Formatter.getFormattedDiffString(diff, format);
    }

    public static String generate(String filePath1, String filePath2) throws Exception {
        return generate(filePath1, filePath2, "stylish");
    }

    // Формат данных берём на основе расширения файла, отрезая точку от строки.
    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
