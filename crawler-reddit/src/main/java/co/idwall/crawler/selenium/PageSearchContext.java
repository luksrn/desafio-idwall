package co.idwall.crawler.selenium;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

public abstract class PageSearchContext {

	private SearchContext searchable;
	
	public PageSearchContext(SearchContext searchable) {
		this.searchable = searchable;
	}
	
	public Optional<WebElement> findByCssSelector(String cssSelector) {
		try {
			WebElement element = searchable.findElement(By.cssSelector(cssSelector));
			return Optional.of(element);
		} catch (NoSuchElementException e) {
			return Optional.empty();
		}
	}
	
	public List<WebElement> findAllByCssSelector(String cssSelector) {
		return searchable.findElements(By.cssSelector(cssSelector));
	}
}
