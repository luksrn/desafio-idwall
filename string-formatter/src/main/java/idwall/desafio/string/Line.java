package idwall.desafio.string;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringJoiner;

public class Line {
	
	private int maxLength;
	private int length;
	private List<String> words = new ArrayList<>();
	/**
	 * Indicates that a word \n has been entered.
	 * Even if there is space available on this line, 
	 * it will be seen as complete.
	 */
	private boolean isNewLineEntered;
	
	Line(int maxLength) {
		this.maxLength = maxLength;
	}
	
	/**
	 * Include a new word in the line or indicate its premature completion with a \n.
	 * 
	 * @param word or \n.
	 * 
	 * @return true if the word fits in line.
	 */
	public boolean addWord(String word) {
		if( !isNewLineEntered ) {
			isNewLineEntered = word.equals("\n");
		}
		boolean canAddWord = !isNewLineEntered && (word.length() + getLength() + getWhitspaces() ) < maxLength;
		
		if( canAddWord ) {
			words.add(word);
			length += word.length();
		}
		return canAddWord;
	}
	
	public String joiner() {
		return joiner(" ");
	}

	public String joiner(String delimiter) {
		StringJoiner joiner = new StringJoiner(delimiter);
		getWords().forEach(joiner::add);
		return joiner.toString();
	}
	
	public boolean isEmpty() {
		return length == 0;
	}
	
	public List<String> getWords() {
		return Collections.unmodifiableList(words);
	}

	public int getMaxLength() {
		return maxLength;
	}
	
	public int getLength() {
		return length;
	}
	
	public int getWhitspaces() {
		return getWords().size() - 1;
	}
}