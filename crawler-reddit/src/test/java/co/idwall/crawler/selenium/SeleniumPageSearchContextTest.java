package co.idwall.crawler.selenium;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import co.idwall.crawler.common.PageElement;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;

public class SeleniumPageSearchContextTest {

	@Mock
	WebDriver webDriver;
	
	@Captor 
    ArgumentCaptor<ByCssSelector> captorCssSelector;
    
	
    @Before
	public void init(){
    	MockitoAnnotations.initMocks(this);
	}
	
    
    // NOT READY
	@Test
	public void testWhenElementNotFoundEmptyWebElement() {
		
		//given
		FakeSeleniumPage page = new FakeSeleniumPage(webDriver);
		when(webDriver.findElement(captorCssSelector.capture())).thenThrow(new NoSuchElementException(""));
		
		//when		
		Optional<PageElement> element = page.findByCssSelector("class");
		
		//then		
		verify(webDriver, Mockito.atLeastOnce()).findElement(any(By.ByCssSelector.class));
		Assert.assertFalse(element.isPresent());
		Assert.assertEquals("By.cssSelector: class", captorCssSelector.getValue().toString());
	}
	
	class FakeSeleniumPage extends SeleniumPageSearchContext {

		public FakeSeleniumPage(SearchContext searchable) {
			super(searchable);
		}
		
	}
}
