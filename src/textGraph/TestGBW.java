package textGraph;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class TestGBW {
	private Graph g;
	private ArrayList<Integer> expected;
	@Before
	public void setUp() {
		AllFunction allFunction_test = new AllFunction();
		g = allFunction_test.createDriectedGraph("/Users/apple/holynovalf/Trash/test2.txt");
		expected = new ArrayList<Integer>();
	}

	@Test
	public void test() {
		expected.add(g.getIndex("victory"));
		assertEquals("Not Equal!", expected, g.getBridges("football", "what"));
	}
}
