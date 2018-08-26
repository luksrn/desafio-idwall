package co.idwall.crawler.reddit.pages;

import org.openqa.selenium.WebElement;

import co.idwall.crawler.reddit.Link;
import co.idwall.crawler.reddit.SubRedditPost;
import co.idwall.crawler.selenium.PageElement;

public class PostPageElement extends PageElement {

	public PostPageElement(WebElement webElement) {
		super(webElement);
	}
	
	public boolean isPromoted() {
		return findByCssSelector(".sponsored-indicator").isPresent();
	}

	public Long getUpvotes() {
		return findByCssSelector(".score.unvoted[title]")
			.map( we -> we.getAttribute("title") )
			.map( Long::parseLong )
			.orElse(0L);
	}

	public String getTitle() {
		return findByCssSelector("a[data-event-action='title']")
			.map(WebElement::getText)
			.get();
	}

	public Link getSelfLink() {
		return findByCssSelector("a[data-event-action='title']")
				.map( we -> we.getAttribute("href"))
				.map(Link::new)
				.get();
	}


	public Link getCommentsLink() {		
		return findByCssSelector(".comments")
			.map( we -> we.getAttribute("href"))
			.map(Link::new)
			.orElse(new Link("#"));
	}
	
	public SubRedditPost toPost() {
		return new SubRedditPost(getUpvotes(), getTitle(), getSelfLink(), getCommentsLink());
	}
}
