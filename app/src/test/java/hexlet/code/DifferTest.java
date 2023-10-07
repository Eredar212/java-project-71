package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DifferTest {
    /*private final String expected = """
            {
              - follow: false
                host: hexlet.io
              - proxy: 123.234.53.22
              - timeout: 50
              + timeout: 20
              + verbose: true
            }""";*/
    private final String compositeExpected = """
            {
                chars1: [a, b, c]
              - chars2: [d, e, f]
              + chars2: false
              - checked: false
              + checked: true
              - default: null
              + default: [value1, value2]
              - id: 45
              + id: null
              - key1: value1
              + key2: value2
                numbers1: [1, 2, 3, 4]
              - numbers2: [2, 3, 4, 5]
              + numbers2: [22, 33, 44, 55]
              - numbers3: [3, 4, 5]
              + numbers4: [4, 5, 6]
              + obj1: {nestedKey=value, isNested=true}
              - setting1: Some value
              + setting1: Another value
              - setting2: 200
              + setting2: 300
              - setting3: true
              + setting3: none
            }""";
    private final String expectedPlain = """
            Property 'chars2' was updated. From [complex value] to false
            Property 'checked' was updated. From false to true
            Property 'default' was updated. From null to [complex value]
            Property 'id' was updated. From 45 to null
            Property 'key1' was removed
            Property 'key2' was added with value: 'value2'
            Property 'numbers2' was updated. From [complex value] to [complex value]
            Property 'numbers3' was removed
            Property 'numbers4' was added with value: [complex value]
            Property 'obj1' was added with value: [complex value]
            Property 'setting1' was updated. From 'Some value' to 'Another value'
            Property 'setting2' was updated. From 200 to 300
            Property 'setting3' was updated. From true to 'none'""";
/*    @Test
    public void getDiffTest() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        assertThat(Differ.generate(path1, path2, "stylish")).isEqualTo(expected);
    }*/
/*    @Test
    public void getDiffTestYml() throws Exception {
        String path1yml = "src/test/resources/file1.yml";
        String path2yml = "src/test/resources/file2.yml";
        assertThat(Differ.generate(path1yml, path2yml, "stylish")).isEqualTo(expected);
    }*/
    @Test
    public void getDiffTestYmlComposite() throws Exception {
        String path1yml = "src/test/resources/file1Composite.yml";
        String path2yml = "src/test/resources/file2Composite.yml";
        assertThat(Differ.generate(path1yml, path2yml, "stylish")).isEqualTo(compositeExpected);
    }
    @Test
    public void getDiffTestJsonComposite() throws Exception {
        String path1json = "src/test/resources/file1Composite.json";
        String path2json = "src/test/resources/file2Composite.json";
        assertThat(Differ.generate(path1json, path2json, "stylish")).isEqualTo(compositeExpected);
    }
    @Test
    public void getDiffTestYmlCompositePlain() throws Exception {
        String path1yml = "src/test/resources/file1Composite.yml";
        String path2yml = "src/test/resources/file2Composite.yml";
        assertThat(Differ.generate(path1yml, path2yml, "plain")).isEqualTo(expectedPlain);
    }
    @Test
    public void getDiffTestJsonCompositePlain() throws Exception {
        String path1json = "src/test/resources/file1Composite.json";
        String path2json = "src/test/resources/file2Composite.json";
        assertThat(Differ.generate(path1json, path2json, "plain")).isEqualTo(expectedPlain);
    }
    @Test
    public void wrongPaths() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file3.json";
        assertThrows(Exception.class, () -> Differ.generate(path1, path2, ""));
    }
    @Test
    public void wrongExtension() throws Exception {
        String path1 = "src/test/resources/file1.txt";
        String path2 = "src/test/resources/file3.txt";
        assertThrows(Exception.class, () -> Differ.generate(path1, path2, ""));
    }
}
