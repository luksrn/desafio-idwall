package co.idwall.crawler.selenium;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebDriver;

public class PageTest {

	@Mock
	private WebDriver webDriver;
	
    @Before
	public void init(){
    	MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testIfGoTakesUserToAnotherPage() {
		
		//given
		FakePage fake = new FakePage(webDriver, "https://fake.com");
		
		//when
		fake.go();
		
		//then
		Mockito.verify(webDriver).get("https://fake.com");			
	}

	class FakePage extends Page {

		public FakePage(WebDriver webDriver, String url) {
			super(webDriver, url);
		}
	}
}
