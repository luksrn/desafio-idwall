package idwall.desafio.string;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import idwall.desafio.string.BasicLineFormatter;
import idwall.desafio.string.IdwallStringFormatter;
import idwall.desafio.string.JustifyLineFormatter;
import idwall.desafio.string.LineFormatter;

class IdwallFormatterTest {
	
	@DisplayName("Test the text format algorithm.")
	@ParameterizedTest
	@MethodSource("textIdwallFormatterProvider")
	void testCompression(String input, String expected, LineFormatter formatter) {
		
		IdwallStringFormatter instance = new IdwallStringFormatter(40,formatter);
		String actual = instance.format(input);
		assertThat(actual, equalTo(expected));
	}
	
	static Stream<Arguments> textIdwallFormatterProvider() {
		
		final String DEFAULT_INPUT_TEXT = "In the beginning God created the heavens and the earth. Now the earth was formless and empty, darkness was over the surface of the deep, and the Spirit of God was hovering over the waters.\n" +
	            "\n" +
	            "And God said, \"Let there be light,\" and there was light. God saw that the light was good, and he separated the light from the darkness. God called the light \"day,\" and the darkness he called \"night.\" And there was evening, and there was morning - the first day.";
	    
		final String TEXT_FORMATTED_CASE_A = 
				"In the beginning God created the heavens\n" + 
				"and the earth. Now the earth was\n" + 
				"formless and empty, darkness was over\n" + 
				"the surface of the deep, and the Spirit\n" + 
				"of God was hovering over the waters.\n" + 
				"\n" + 
				"And God said, \"Let there be light,\" and\n" + 
				"there was light. God saw that the light\n" + 
				"was good, and he separated the light\n" + 
				"from the darkness. God called the light\n" + 
				"\"day,\" and the darkness he called\n" + 
				"\"night.\" And there was evening, and\n" + 
				"there was morning - the first day.";
		
		final String TEXT_FORMATTED_CASE_B = 
				"In the beginning God created the heavens\n" + 
				"and    the  earth.  Now  the  earth  was\n" + 
				"formless  and  empty,  darkness was over\n" + 
				"the  surface of the deep, and the Spirit\n" + 
				"of  God  was  hovering  over the waters.\n" + 
				"\n" + 
				"And  God said, \"Let there be light,\" and\n" + 
				"there  was light. God saw that the light\n" + 
				"was  good,  and  he  separated the light\n" + 
				"from  the darkness. God called the light\n" + 
				"\"day,\"    and  the  darkness  he  called\n" + 
				"\"night.\"  And  there  was  evening,  and\n" + 
				"there  was  morning  -  the  first  day."; 
		
	    return Stream.of(
	        Arguments.of(DEFAULT_INPUT_TEXT, TEXT_FORMATTED_CASE_A, new BasicLineFormatter()),
	        Arguments.of(DEFAULT_INPUT_TEXT, TEXT_FORMATTED_CASE_B, new JustifyLineFormatter())
	    );
	}
	
}
