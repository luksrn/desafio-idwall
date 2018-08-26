package idwall.desafio.string;

import static java.util.stream.Collectors.joining;

/**
 * Created by Rodrigo Catão Araujo on 06/02/2018.
 */
public class IdwallStringFormatter {

	private Integer limit;
	private LineFormatter lineFormatter;

    public IdwallStringFormatter() {
        this(40, new BasicLineFormatter());
    }
    
    public IdwallStringFormatter(Integer limit, LineFormatter formatter) {
        this.limit = limit;
        this.lineFormatter = formatter;
    }

    /**
     * It receives a text and should return it formatted
     *
     * @param text
     * @return
     */
    public String format(String text) {
    	return new StringToLinesParser(text, limit).getLines()
				    			.stream()
				    			.map(this.lineFormatter::format)
				    			.collect(joining("\n"));
    }
}
