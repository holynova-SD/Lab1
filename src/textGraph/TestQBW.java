package textGraph;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TestQBW {
	private AllOperations operableGraph;
	private Graph g;
	String lword, rword;
	String expected;
	@Before
	public void setUp() {
		operableGraph = new AllOperations();
		g = operableGraph.createDriectedGraph("/Users/apple/holynovalf/Trash/test2.txt");
	}
	@Parameters
	public static List<String[]> prepareData() {
		String[][] parameters = new String[][] {
			{"No bridge word from football to victory!", "football", "victory"},
			{"The bridge word from football to what is: victory.", "football", "what"},
			{"The bridge words from go to back are: over, lunch and home.", "go", "back"}};
		return Arrays.asList(parameters);
	}
	
	public TestQBW(String expected, String lword, String rword) {
		this.expected = expected;
		this.lword = lword;
		this.rword = rword;
	}
	
	@Test
	public void testQBW() {
		assertEquals("Not Equal!", expected, operableGraph.queryBridgeWords(g, lword, rword));
	}

}

