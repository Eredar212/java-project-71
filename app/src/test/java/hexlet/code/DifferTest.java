package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DifferTest {
    private static String compositeExpected;
    private static String expectedPlain;

    @BeforeAll
    static void init() throws IOException {
        Path stylishPath = Paths.get("src/test/resources/StylishExpected").toAbsolutePath().normalize();
        compositeExpected = Files.readString(stylishPath);
        Path plainPath = Paths.get("src/test/resources/PlainExpected").toAbsolutePath().normalize();
        expectedPlain = Files.readString(plainPath);
    }

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
        String path2 = "src/test/resources/file1.txt";
        assertThrows(Exception.class, () -> Differ.generate(path1, path2, ""));
    }

    @Test
    public void wrongFormat() throws Exception {
        String path1 = "src/test/resources/file1.json";
        String path2 = "src/test/resources/file2.json";
        assertThrows(Exception.class, () -> Differ.generate(path1, path2, "txt"));
    }
}
