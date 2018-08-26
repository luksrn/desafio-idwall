package idwall.desafio.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BasicLineFormatterTest {
	
	@Test
	void testBasicFormatter() {
		
		LineFormatter formatter = new BasicLineFormatter();
		
		Line line = new Line(40);
		line.addWord("Lucas");
		line.addWord("Farias");

		String s = formatter.format(line);
		Assertions.assertEquals( "Lucas Farias", s);
		Assertions.assertEquals( 12, s.length());
	}

	@Test
	void testBasicFormatterWithOneWord() {
		
		LineFormatter formatter = new BasicLineFormatter();
		
		Line line = new Line(40);
		line.addWord("Lucas");

		String s = formatter.format(line);
		Assertions.assertEquals( "Lucas", s);
		Assertions.assertEquals( 5, s.length());
	}
}
