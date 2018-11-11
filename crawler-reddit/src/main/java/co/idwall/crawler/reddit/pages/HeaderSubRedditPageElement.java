package co.idwall.crawler.reddit.pages;

import co.idwall.crawler.common.PageElement;

public class HeaderSubRedditPageElement {

    private PageElement pageElement;

    public HeaderSubRedditPageElement(PageElement pageElement) {
		this.pageElement = pageElement;
	}
	
	public void clickTop() {
        pageElement.findByCssSelector(".tabmenu li:nth-child(5) a")
			.ifPresent(PageElement::click);
	}
}
