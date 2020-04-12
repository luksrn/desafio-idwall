package co.idwall.crawler.reddit.pages;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubRedditPageTest {
	
	/*@Mock
	WebDriver webDriver;
	
	@Captor
	ArgumentCaptor<ByCssSelector> cssSelecctor;
	
	@Captor
	ArgumentCaptor<String> stringCaptor;
	
	SubRedditPage instance;
	
	@Before
	public void init(){
    	MockitoAnnotations.initMocks(this);
    	instance = new SubRedditPage(webDriver,"cats");
	}
	
	@Test
	public void testConstrucaoDoSubReddit() {
		assertEquals( "cats", instance.getSubReddit() );
		assertEquals( "https://old.reddit.com/r/cats", instance.getUrl() );
	}

	@Test
	public void testNavegacaoParaProximaPaginaDoSubReddit() {
		
		WebElement linkNavegacao = mock(WebElement.class);
		when(webDriver.findElement(cssSelecctor.capture())).thenReturn(linkNavegacao);

		instance.nextPage();
		
		assertEquals("By.cssSelector: .next-button a", cssSelecctor.getValue().toString());
		verify(linkNavegacao, Mockito.atLeastOnce() ).click();
	}

	
	@Test
	public void testEncontrarHeaderDaPagina() {
		
		WebElement divHeader = mock(WebElement.class);
		when(webDriver.findElement(cssSelecctor.capture())).thenReturn(divHeader);

		HeaderSubRedditPageElement headerPageElement = instance.header();
		
		assertEquals(divHeader, headerPageElement.getWebElement());
		assertEquals("By.cssSelector: #header", cssSelecctor.getValue().toString());		
	}
	
	@Test
	public void testEncontrarPostsDaPagina() {
		
		WebElement divPostA = mock(WebElement.class);
		WebElement divPostB = mock(WebElement.class);
		
		when(webDriver.findElements(cssSelecctor.capture())).thenReturn(Arrays.asList(divPostA,divPostB));

		List<PostPageElement> posts = instance.findPosts();
		

		assertEquals("By.cssSelector: div[data-subreddit]", cssSelecctor.getValue().toString());
		assertEquals(divPostA, posts.get(0).getWebElement());
		assertEquals(divPostB, posts.get(1).getWebElement());
		
	}*/
}
