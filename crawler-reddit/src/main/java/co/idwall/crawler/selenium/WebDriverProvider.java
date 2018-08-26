package co.idwall.crawler.selenium;

import org.openqa.selenium.WebDriver;

@FunctionalInterface
public interface WebDriverProvider {

	WebDriver get();
}
