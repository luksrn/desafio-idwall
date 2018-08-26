package co.idwall.crawler.reddit.pages;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebElement;

public class HeaderSubRedditPageElementTest {
	
	@Mock
	WebElement webElement;

    @Captor 
    ArgumentCaptor<ByCssSelector> captorCssSelector;
    
    @Before
	public void init(){
    	MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		// given
		HeaderSubRedditPageElement header = new HeaderSubRedditPageElement(webElement);
		WebElement topLink = mock(WebElement.class);
		when(webElement.findElement(any(ByCssSelector.class))).thenReturn(topLink);
		
		//when
		header.clickTop();
		
		//then
		verify(webElement).findElement(captorCssSelector.capture());		
		verify(webElement, atLeastOnce()).findElement(any(ByCssSelector.class));
		verify(topLink, atLeastOnce()).click();
		Assert.assertEquals("By.cssSelector: .tabmenu li:nth-child(5)", captorCssSelector.getValue().toString() );
	}

}
