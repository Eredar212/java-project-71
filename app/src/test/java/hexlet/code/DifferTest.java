package hexlet.code;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class DifferTest {
	public Map<String, Object> map1;
	public Map<String, Object> map2;
	@BeforeEach
	public void init() {
		map1 = Map.of("host", "hexlet.io", "timeout", 50, "proxy", "123.234.53.22", "follow", false);
		map2 = Map.of("timeout", 20, "verbose", true, "host", "hexlet.io");
	}
	@Test
	public void getDiffTest() {
		String expected = "- follow: false\n" +
				"  host: hexlet.io\n" +
				"- proxy: 123.234.53.22\n" +
				"- timeout: 50\n" +
				"+ timeout: 20\n" +
				"+ verbose: true";
		assertThat(Differ.getDiff(map1, map2)).isEqualTo(expected);
	}
}