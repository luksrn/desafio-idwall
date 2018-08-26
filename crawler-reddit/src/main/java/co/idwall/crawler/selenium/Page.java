package co.idwall.crawler.selenium;

import org.openqa.selenium.WebDriver;

public abstract class Page extends PageSearchContext {
	
	protected WebDriver webDriver;
	
	private String url;

	public Page(WebDriver webDriver, String url) {
		super(webDriver);
		this.webDriver = webDriver;
		this.url = url;
	}
	
	public void go() {
		webDriver.get(url);
	}
	
	public String getUrl() {
		return url;
	}
}
