package co.idwall.crawler.selenium;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class RemoteWebDriverProvider implements WebDriverProvider {

	private String url;
	private DesiredCapabilities dc;

	public RemoteWebDriverProvider() {
		this.url = "http://localhost:4444/wd/hub";
		this.dc = DesiredCapabilities.firefox();
	}
	
	public RemoteWebDriverProvider(String url, DesiredCapabilities dc) {
		this.url = url;
		this.dc = dc;
	}
	
	@Override
	public WebDriver get() {
		try {
		    RemoteWebDriver webDriver = new RemoteWebDriver(new URL(url), dc);
			return webDriver;
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
}
