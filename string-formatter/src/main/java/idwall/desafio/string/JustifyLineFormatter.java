package idwall.desafio.string;

public class JustifyLineFormatter implements LineFormatter {

	@Override
	public String format(Line line) {
		
		if(line.isEmpty()) {
			return "";
		}
		
		StringBuilder output = new StringBuilder(line.joiner());
		
		if(line.getWords().size() == 1) {
			return output.toString();
		}
		
		int aditionalSpaces = line.getMaxLength() - (line.getLength() + line.getWhitspaces());
		fillLineWithWhiteSpaces(output, aditionalSpaces);
		
		return output.toString();
	}

	private void fillLineWithWhiteSpaces(StringBuilder output, int aditionalSpaces) {
		
		while ( aditionalSpaces > 0 ) {
			for( int i = 0 ; i < output.length() && aditionalSpaces > 0 ; i++ ) {
				if( output.charAt(i) == ' ') {
					output.insert(i, ' ');
					i++;
					aditionalSpaces--;
				}
			}
		}
	}
}
