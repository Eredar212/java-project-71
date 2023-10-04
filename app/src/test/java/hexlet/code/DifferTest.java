package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class DifferTest {
    public Map<String, Object> map1;
    public Map<String, Object> map2;

    /*@BeforeEach
    public void init() {
        map1 = Map.of("host", "hexlet.io", "timeout", 50, "proxy", "123.234.53.22", "follow", false);
        map2 = Map.of("timeout", 20, "verbose", true, "host", "hexlet.io");
    }*/
    @Test
    public void getDiffTest() throws Exception {
        String expected = "- follow: false\n" + "  host: hexlet.io\n" + "- proxy: 123.234.53.22\n" + "- timeout: 50\n"
                + "+ timeout: 20\n" + "+ verbose: true";
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        assertThat(Differ.generate(path1, path2, "")).isEqualTo(expected);
    }

    @Test
    public void worongPaths() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file3.json";
        assertThrows(Exception.class, () -> Differ.generate(path1, path2, ""));
    }
}
