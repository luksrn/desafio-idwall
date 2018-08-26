package co.idwall.crawler.reddit.pages;

import org.openqa.selenium.WebElement;

import co.idwall.crawler.selenium.PageElement;

public class HeaderSubRedditPageElement  extends PageElement {

	public HeaderSubRedditPageElement(WebElement webElement) {
		super(webElement);
	}
	
	public void clickTop() {
		findByCssSelector(".tabmenu li:nth-child(5)")
			.ifPresent(WebElement::click);
	}
}
