package co.idwall.crawler.selenium;

import co.idwall.crawler.common.Page;
import org.openqa.selenium.WebDriver;

public class SeleniumPage extends SeleniumPageSearchContext implements Page {
	
	protected WebDriver webDriver;
	
	private String url;

	public SeleniumPage(WebDriver webDriver, String url) {
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
