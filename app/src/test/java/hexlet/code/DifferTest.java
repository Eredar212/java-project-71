package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public final class DifferTest {
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

    @ParameterizedTest(name = "[{index}] {arguments}")
    @CsvSource(useHeadersInDisplayName = true, value = {
        "In, Out, Expected",
        "json, stylish, expectedStylishDefault",
        "json, '', expectedStylishDefault",
        "json, plain, expectedPlain",
        "json, json, expectedJson",
        "yml, stylish, expectedStylishDefault",
        "yml, '', expectedStylishDefault",
        "yml, plain, expectedPlain",
        "yml, json, expectedJson"
    })
    void generateTest(String dataFormat, String outputFormat, String expected) {
        String path1yml = "src/test/resources/fixtures/answers/file1Composite." + dataFormat;
        String path2yml = "src/test/resources/fixtures/answers/file2Composite." + dataFormat;
        try {
            assertThat(Differ.generate(path1yml, path2yml, outputFormat))
                    .isEqualTo(this.getClass().getDeclaredField(expected).get(this));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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
