package idwall.desafio.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LineTest {

	@Test
	void testLinhaVazia() {
		Line l = new Line(50);
		Assertions.assertTrue(l.isEmpty());
	}
	
	@Test
	void testIfEmptyStringKeepsLineEmpty() {
		Line l = new Line(50);
		l.addWord("");
		Assertions.assertTrue(l.isEmpty());
	}
	
	@Test
	void testLineProperties() {
		Line l = new Line(15);
		l.addWord("Lucas");
		Assertions.assertFalse(l.isEmpty());
		Assertions.assertEquals(5, l.getLength());
		Assertions.assertEquals(0, l.getWhitspaces());
	}
	
	@Test
	void testLinePropertiesWithTwoWords() {
		Line l = new Line(15);
		l.addWord("Lucas");
		l.addWord("Farias");
		Assertions.assertFalse(l.isEmpty());
		Assertions.assertEquals(11, l.getLength());
		Assertions.assertEquals(1, l.getWhitspaces());
	}
	
	@Test
	void testLinePropertiesWithLargeSize() {
		Line l = new Line(6);
		l.addWord("Lucas");
		Assertions.assertFalse(l.addWord("Farias"));
		Assertions.assertEquals(5, l.getLength());
		Assertions.assertEquals(0, l.getWhitspaces());
	}
}
