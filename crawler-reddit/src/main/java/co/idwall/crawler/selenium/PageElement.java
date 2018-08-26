package co.idwall.crawler.selenium;

import org.openqa.selenium.WebElement;

public abstract class PageElement extends PageSearchContext {

	protected WebElement webElement;

	public PageElement(WebElement webElement) {
		super(webElement);
		this.webElement = webElement;
	}
	
	public WebElement getWebElement() {
		return webElement;
	}
}
