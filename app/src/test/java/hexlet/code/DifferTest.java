package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;



public class DifferTest {
    private String expected;
    @BeforeEach
    public void init() {
        expected = "- follow: false\n" + "  host: hexlet.io\n" + "- proxy: 123.234.53.22\n" + "- timeout: 50\n"
                + "+ timeout: 20\n" + "+ verbose: true";
    }
    @Test
    public void getDiffTest() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        assertThat(Differ.generate(path1, path2, "")).isEqualTo(expected);
    }

    @Test
    public void getDiffTestYml() throws Exception {
        String path1yml = "src/test/resources/file1.yml";
        String path2yml = "src/test/resources/file2.yml";
        assertThat(Differ.generate(path1yml, path2yml, "")).isEqualTo(expected);
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
