package co.idwall.crawler.reddit.pages;

import co.idwall.crawler.common.PageElement;
import co.idwall.crawler.reddit.Link;
import co.idwall.crawler.reddit.SubRedditPost;

public class PostPageElement {

	private PageElement pageElement;

	public PostPageElement(PageElement pageElement) {
		this.pageElement = pageElement;
	}
	
	public boolean isPromoted() {
		return pageElement.findByCssSelector(".sponsored-indicator").isPresent();
	}

	public Long getUpvotes() {
		return pageElement.findByCssSelector(".score.unvoted[title]")
			.map( we -> we.getAttribute("title") )
			.map( Long::parseLong )
			.orElse(0L);
	}

	public String getTitle() {
		return pageElement.findByCssSelector("a[data-event-action='title']")
			.map(PageElement::getText)
			.get();
	}

	public Link getSelfLink() {
		return pageElement.findByCssSelector("a[data-event-action='title']")
				.map( we -> we.getAttribute("href"))
				.map(Link::new)
				.get();
	}


	public Link getCommentsLink() {		
		return pageElement.findByCssSelector(".comments")
			.map( we -> we.getAttribute("href"))
			.map(Link::new)
			.orElse(new Link("#"));
	}
	
	public SubRedditPost toPost() {
		return new SubRedditPost(getUpvotes(), getTitle(), getSelfLink(), getCommentsLink());
	}
}
