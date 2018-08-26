package idwall.desafio.string;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringToLinesParserTest {

	@Test
	void testFormatBasicOneLineEmpty() {
		StringToLinesParser StringToLinesParser = new StringToLinesParser("", 15);
		Assertions.assertEquals(0, StringToLinesParser.getLines().size());
	}
	
	
	@Test
	void testFormatBasicOneLine() {
		StringToLinesParser StringToLinesParser = new StringToLinesParser("Lucas Farias", 15);
		Assertions.assertEquals(1, StringToLinesParser.getLines().size());
	}
	
	@Test
	void testFormatBasicMoreThenOneLine() {
		
		StringToLinesParser StringToLinesParser = new StringToLinesParser("Lucas Farias de Oliveira\n"
				+ "\n"
				+ "Linha três", 15);
		Assertions.assertEquals( 4, StringToLinesParser.getLines().size() );
		
		StringToLinesParser = new StringToLinesParser("Lucas Farias de Oliveira\n"
				+ "  \n"
				+ "\n"
				+ "Linha três", 15);
		Assertions.assertEquals( 5, StringToLinesParser.getLines().size() );
	}
	

	@Test
	void testStrangeThings() {
		Assertions.assertThrows(IllegalArgumentException.class, 
									() -> new StringToLinesParser("LucasFariasDeOliveira", 5) ) ;
	}
}
