package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DifferTest {
    private static String expectedStylishDefault;
    private static String expectedPlain;
    private static String expectedJson;

    private static Path getFixturePath(String fileName) {
        return Paths.get("src", "test", "resources", "fixtures", "expectedResults", fileName)
                .toAbsolutePath().normalize();
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = getFixturePath(fileName);
        return Files.readString(filePath).trim();
    }

    // Побочные эффекты правильно делать не на уровне класса, а внутри хуков
    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedJson = readFixture("result_json.json");
        expectedPlain = readFixture("PlainExpected");
        expectedStylishDefault = readFixture("StylishExpected");
    }

    //test yml -> stylish, default
    @Test
    public void getDiffTestYmlComposite() throws Exception {
        String path1yml = "src/test/resources/fixtures/answers/file1Composite.yml";
        String path2yml = "src/test/resources/fixtures/answers/file2Composite.yml";
        assertThat(Differ.generate(path1yml, path2yml, "stylish")).isEqualTo(expectedStylishDefault);
        assertThat(Differ.generate(path1yml, path2yml, "")).isEqualTo(expectedStylishDefault);
    }
    //json -> stylish, default
    @Test
    public void getDiffTestJsonComposite() throws Exception {
        String path1json = "src/test/resources/fixtures/answers/file1Composite.json";
        String path2json = "src/test/resources/fixtures/answers/file2Composite.json";
        assertThat(Differ.generate(path1json, path2json, "stylish")).isEqualTo(expectedStylishDefault);
        assertThat(Differ.generate(path1json, path2json, "")).isEqualTo(expectedStylishDefault);
    }
    //yml -> plain
    @Test
    public void getDiffTestYmlCompositePlain() throws Exception {
        String path1yml = "src/test/resources/fixtures/answers/file1Composite.yml";
        String path2yml = "src/test/resources/fixtures/answers/file2Composite.yml";
        assertThat(Differ.generate(path1yml, path2yml, "plain")).isEqualTo(expectedPlain);
    }
    //json -> plain
    @Test
    public void getDiffTestJsonCompositePlain() throws Exception {
        String path1json = "src/test/resources/fixtures/answers/file1Composite.json";
        String path2json = "src/test/resources/fixtures/answers/file2Composite.json";
        assertThat(Differ.generate(path1json, path2json, "plain")).isEqualTo(expectedPlain);
    }
    @Test
    //yml -> json
    public void getDiffTestYmlCompositeJson() throws Exception {
        String path1yml = "src/test/resources/fixtures/answers/file1Composite.yml";
        String path2yml = "src/test/resources/fixtures/answers/file2Composite.yml";
        assertThat(Differ.generate(path1yml, path2yml, "json")).isEqualTo(expectedJson);
    }
    //json -> json
    @Test
    public void getDiffTestJsonCompositeJson() throws Exception {
        String path1json = "src/test/resources/fixtures/answers/file1Composite.json";
        String path2json = "src/test/resources/fixtures/answers/file2Composite.json";
        assertThat(Differ.generate(path1json, path2json, "json")).isEqualTo(expectedJson);
    }

    @Test
    public void wrongPaths() throws Exception {
        String path1 = "src/test/resources/fixtures/other/file1.json";
        String path2 = "WRONG/PATH/ile2.json";
        assertThrows(Exception.class, () -> Differ.generate(path1, path2, ""));
    }

    @Test
    public void wrongExtension() throws Exception {
        String path1 = "src/test/resources/fixtures/other/file1.txt";
        String path2 = "src/test/resources/fixtures/other/file1.WRONG_EXTENSION";
        assertThrows(Exception.class, () -> Differ.generate(path1, path2, ""));
    }

    @Test
    public void wrongFormat() throws Exception {
        String path1 = "src/test/resources/fixtures/other/file1.json";
        String path2 = "src/test/resources/fixtures/other/file2.json";
        assertThrows(Exception.class, () -> Differ.generate(path1, path2, "WRONG_FORMAT"));
    }
}
