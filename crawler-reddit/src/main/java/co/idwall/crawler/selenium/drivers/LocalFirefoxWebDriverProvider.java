package co.idwall.crawler.selenium.drivers;

import co.idwall.crawler.selenium.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalFirefoxWebDriverProvider implements WebDriverProvider {
	
	private String pathGeckoDriver;
	
	public LocalFirefoxWebDriverProvider() {
		this("drivers/geckodriver");
	}
	
	public LocalFirefoxWebDriverProvider(String pathGeckoDriver) {
		this.pathGeckoDriver = pathGeckoDriver;
	}

	@Override
	public WebDriver get() {
		System.setProperty("webdriver.gecko.driver", pathGeckoDriver);
		return new FirefoxDriver();
	}

}
