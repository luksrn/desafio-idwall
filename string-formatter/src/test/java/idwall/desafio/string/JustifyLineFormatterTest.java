package idwall.desafio.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JustifyLineFormatterTest {
	
	@Test
	void testJustifyFormatter() {
		
		LineFormatter formatter = new JustifyLineFormatter();
		
		Line line = new Line(40);
		line.addWord("and");
		line.addWord("the");
		line.addWord("earth.");
		line.addWord("Now");
		line.addWord("the");
		line.addWord("earth");
		line.addWord("was");
		
		String s = formatter.format(line);
		Assertions.assertEquals( "and    the  earth.  Now  the  earth  was",s );
		Assertions.assertEquals( 40 , s.length() );
	}

	@Test
	void testJustifyFormatterWithOneWord() {
		
		LineFormatter formatter = new JustifyLineFormatter();
		
		Line line = new Line(40);
		line.addWord("and");
		
		String s = formatter.format(line);
		Assertions.assertEquals( "and",s );
		Assertions.assertEquals( 3 , s.length() );
	}

}
