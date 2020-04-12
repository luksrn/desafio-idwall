package co.idwall.crawler.selenium;

import co.idwall.crawler.common.PageElement;
import co.idwall.crawler.common.PageSearchContext;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SeleniumPageSearchContext implements PageSearchContext {

	private SearchContext seleniumSearchContext;
	
	public SeleniumPageSearchContext(SearchContext seleniumSearchContext) {
		this.seleniumSearchContext = seleniumSearchContext;
	}
	
	public Optional<PageElement> findByCssSelector(String cssSelector) {
		try {
			org.openqa.selenium.WebElement element = seleniumSearchContext.findElement(By.cssSelector(cssSelector));
			PageElement pageElement = new SeleniumPageElement(element);
			return Optional.of(pageElement);
		} catch (NoSuchElementException e) {
			return Optional.empty();
		}
	}
	
	public List<PageElement> findAllByCssSelector(String cssSelector) {
		return seleniumSearchContext.findElements(By.cssSelector(cssSelector))
				.stream()
				.map(SeleniumPageElement::new)
				.collect(Collectors.toList());
	}
}
