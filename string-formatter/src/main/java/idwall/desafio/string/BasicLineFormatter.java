package idwall.desafio.string;

public class BasicLineFormatter  implements LineFormatter {

	@Override
	public String format(Line line) {
		return line.joiner();
	}
}
