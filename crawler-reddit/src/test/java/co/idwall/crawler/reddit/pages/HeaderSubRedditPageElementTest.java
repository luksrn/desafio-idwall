package co.idwall.crawler.reddit.pages;

import co.idwall.crawler.common.PageElement;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class HeaderSubRedditPageElementTest {

	@Mock
	PageElement pageElement;

    @Before
	public void init(){
    	MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() {
		// given
		HeaderSubRedditPageElement header = new HeaderSubRedditPageElement(pageElement);
		PageElement topLink = mock(PageElement.class);
		when(pageElement.findByCssSelector(".tabmenu li:nth-child(5) a")).thenReturn(Optional.of(topLink));
		
		//when
		header.clickTop();
		
		//then
		verify(topLink, atLeastOnce()).click();
	}

}
