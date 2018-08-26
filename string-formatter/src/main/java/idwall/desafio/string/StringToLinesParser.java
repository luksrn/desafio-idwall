package idwall.desafio.string;

import static java.util.Arrays.stream;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

class StringToLinesParser {

	private List<Line> lines = new ArrayList<>();
	private int cols;
	private String content;
	private Line currentLine;
	
	public StringToLinesParser(String content, int cols) {
		this.cols = cols;
		this.content = content;
		parse();
	}

	private void parse() {
		if(content == null || content.trim().isEmpty()) {
			return;
		}
		currentLine = new Line(cols);
		lines.add(currentLine);
    	new BufferedReader( new StringReader(content))
    			.lines()
    			.forEach(this::processLine);
	}
	
	private void processLine(String line) {
		if(line.trim().isEmpty()) {
			newLine( l -> l.addWord("\n"));
		} else {
			stream(line.split("\\s")).forEach(this::fitsWordOnLine);
		}
	}
	
	private void fitsWordOnLine(String word) {
		if(word.length() > cols ) {
			throw new IllegalArgumentException("Violation: 'As palavras não podem ser quebradas no meio.'");
		}
		// dont' fit in the current line?
		if( !currentLine.addWord(word) ) {
			newLine(l -> l.addWord(word));
		}
	}
	
	private void newLine(Consumer<Line> c) {
		currentLine = new Line(cols);
		c.accept(currentLine);
		lines.add(currentLine);
	}
	
	public List<Line> getLines(){
		return Collections.unmodifiableList(lines);
	}
}