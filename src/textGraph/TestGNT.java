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
public class TestGNT {
	private Features features_test;
	private Graph g;
	String input;
	String expected;
	@Before
	public void setUp() {
		features_test = new Features();
		AllFunction allFunction_test = new AllFunction();
		g = allFunction_test.createDriectedGraph("/Users/apple/holynovalf/Trash/test2.txt");
	}
	
	@Parameters
	public static List<String[]> prepareData() {
		String[][] parameters = new String[][] {
			{"football victory what", "football what"},
			{"go home back", "go back"},
			{"go over back", "go back"},
			{"go lunch back", "go back"},
			{"football , what after ignore", "football , what ignore"},
			{"what after ignore nightwish", "what ignore nightwish"},
			{"football victory", "football victory"}};
		return Arrays.asList(parameters);
	}
	
	public TestGNT(String expected, String input) {
		this.expected = expected;
		this.input = input;
	}
	
	@Test
	public void testGNT() {
		assertEquals("Not Equal!", expected, features_test.generateNewText(g, input));
	}
}

