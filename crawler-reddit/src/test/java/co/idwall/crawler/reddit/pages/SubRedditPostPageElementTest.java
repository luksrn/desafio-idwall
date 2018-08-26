package co.idwall.crawler.reddit.pages;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import co.idwall.crawler.reddit.Link;

public class SubRedditPostPageElementTest {

	@Mock
	WebElement webElement;
	
	@Captor
	ArgumentCaptor<ByCssSelector> cssSelecctor;
	
	@Captor
	ArgumentCaptor<String> stringCaptor;
	
	@Before
	public void init(){
    	MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testEncontrarPostsPromovidos() {
	
		PostPageElement instance = new PostPageElement(webElement);
		WebElement sponsoredIndicator = mock(WebElement.class);
		when(webElement.findElement(cssSelecctor.capture())).thenReturn(sponsoredIndicator);
		
		boolean promotedPost = instance.isPromoted();
		
		assertTrue(promotedPost);
		assertEquals("By.cssSelector: .sponsored-indicator", cssSelecctor.getValue().toString());
	}
	
	@Test
	public void testEncontrarPostsQueNaoSaoPromovidos() {
	
		PostPageElement instance = new PostPageElement(webElement);
		when(webElement.findElement(cssSelecctor.capture())).thenThrow(new NoSuchElementException(""));
		
		boolean promotedPost = instance.isPromoted();
		
		assertFalse(promotedPost);
		assertEquals("By.cssSelector: .sponsored-indicator", cssSelecctor.getValue().toString());
	}
	
	
	@Test
	public void testEncontrarValorDeUpvoteDoPost() {
		PostPageElement instance = new PostPageElement(webElement);
		WebElement divComPontuacao = mock(WebElement.class);
		when(webElement.findElement(cssSelecctor.capture())).thenReturn(divComPontuacao);
		when(divComPontuacao.getAttribute(stringCaptor.capture())).thenReturn("534");
		
		Long upvotes = instance.getUpvotes();
		
		assertEquals( (long)534L, (long)upvotes);
		assertEquals("title", stringCaptor.getValue());
		assertEquals("By.cssSelector: .score.unvoted[title]", cssSelecctor.getValue().toString());
	}
	
	@Test
	public void testQuandoNaoHaPontuacaoEmPost() {
		PostPageElement instance = new PostPageElement(webElement);
		when(webElement.findElement(cssSelecctor.capture())).thenThrow(new NoSuchElementException(""));
		
		Long upvotes = instance.getUpvotes();
		
		assertEquals( (long)0L, (long)upvotes);
		assertEquals("By.cssSelector: .score.unvoted[title]", cssSelecctor.getValue().toString());
	}
	
	@Test
	public void testEncontrarTituloDoPost() {
		PostPageElement instance = new PostPageElement(webElement);
		WebElement linkDoTitulo = mock(WebElement.class);
		when(webElement.findElement(cssSelecctor.capture())).thenReturn(linkDoTitulo);
		when(linkDoTitulo.getText()).thenReturn("Titulo");
		
		String title = instance.getTitle();
		
		assertEquals( "Titulo", title );
		assertEquals("By.cssSelector: a[data-event-action='title']", cssSelecctor.getValue().toString());
	}
	

	@Test
	public void testEncontrarLinkDoPost() {
		PostPageElement instance = new PostPageElement(webElement);
		WebElement linkDoTitulo = mock(WebElement.class);
		when(webElement.findElement(cssSelecctor.capture())).thenReturn(linkDoTitulo);
		when(linkDoTitulo.getAttribute(stringCaptor.capture())).thenReturn("https://link.com");
		
		Link link = instance.getSelfLink();
		
		assertEquals( "https://link.com" , link.getHref() );
		assertEquals( "href", stringCaptor.getValue() );
		assertEquals("By.cssSelector: a[data-event-action='title']", cssSelecctor.getValue().toString());
	}
	
	@Test
	public void testEncontrarLinkDosComentarios() {
		
		PostPageElement instance = new PostPageElement(webElement);
		WebElement linkDosComentarios = mock(WebElement.class);
		when(webElement.findElement(cssSelecctor.capture())).thenReturn(linkDosComentarios);
		when(linkDosComentarios.getAttribute(stringCaptor.capture())).thenReturn("https://comments.link.com");
		
		Link link = instance.getCommentsLink();
		
		assertEquals( "https://comments.link.com" , link.getHref() );
		assertEquals( "href", stringCaptor.getValue() );
		assertEquals("By.cssSelector: .comments", cssSelecctor.getValue().toString());
	}
	

	@Test
	public void testQuandoPostNaoTemLinkDosComentarios() {
		
		PostPageElement instance = new PostPageElement(webElement);
		when(webElement.findElement(cssSelecctor.capture())).thenThrow(new NoSuchElementException(""));
		
		Link link = instance.getCommentsLink();
		
		assertEquals( "#" , link.getHref() );
		assertEquals("By.cssSelector: .comments", cssSelecctor.getValue().toString());
	}
}
