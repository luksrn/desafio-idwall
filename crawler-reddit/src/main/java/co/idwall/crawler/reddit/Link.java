package co.idwall.crawler.reddit;

import java.util.Objects;

public class Link {

	private String href;

	public Link(String href) {
		Objects.requireNonNull(href, "Link is mandatory");
		this.href = href;
	}
	
	public String getHref() {
		return href;
	}
	
	public boolean empty() {
		return href.equals("#");
	}
}
