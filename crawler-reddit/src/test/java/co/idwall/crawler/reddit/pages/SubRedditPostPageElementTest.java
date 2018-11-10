package co.idwall.crawler.reddit.pages;

import co.idwall.crawler.common.PageElement;
import co.idwall.crawler.reddit.Link;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SubRedditPostPageElementTest {

	@Mock
    PageElement webElement;
	
	@Captor
	ArgumentCaptor<String> stringCaptor;
	
	@Before
	public void init(){
    	MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testEncontrarPostsPromovidos() {
	
		PostPageElement instance = new PostPageElement(webElement);
		PageElement sponsoredIndicator = mock(PageElement.class);
		when(webElement.findByCssSelector(".sponsored-indicator")).thenReturn(Optional.of(sponsoredIndicator));
		
		boolean promotedPost = instance.isPromoted();
		
		assertTrue(promotedPost);
	}
	
	@Test
	public void testEncontrarPostsQueNaoSaoPromovidos() {
	
		PostPageElement instance = new PostPageElement(webElement);
        when(webElement.findByCssSelector(".sponsored-indicator")).thenReturn(Optional.empty());
		
		boolean promotedPost = instance.isPromoted();
		
		assertFalse(promotedPost);
	}
	
	
	@Test
	public void testEncontrarValorDeUpvoteDoPost() {
		PostPageElement instance = new PostPageElement(webElement);
		PageElement divComPontuacao = mock(PageElement.class);
		when(webElement.findByCssSelector(".score.unvoted[title]")).thenReturn(Optional.of(divComPontuacao));
		when(divComPontuacao.getAttribute(stringCaptor.capture())).thenReturn("534");
		
		Long upvotes = instance.getUpvotes();
		
		assertEquals( (long)534L, (long)upvotes);
		assertEquals("title", stringCaptor.getValue());
	}
	
	@Test
	public void testQuandoNaoHaPontuacaoEmPost() {
		PostPageElement instance = new PostPageElement(webElement);
        when(webElement.findByCssSelector(".score.unvoted[title]")).thenReturn(Optional.empty());
		
		Long upvotes = instance.getUpvotes();
		
		assertEquals( (long)0L, (long)upvotes);
	}
	
	@Test
	public void testEncontrarTituloDoPost() {
		PostPageElement instance = new PostPageElement(webElement);
		PageElement linkDoTitulo = mock(PageElement.class);
		when(webElement.findByCssSelector("a[data-event-action='title']")).thenReturn(Optional.of(linkDoTitulo));
		when(linkDoTitulo.getText()).thenReturn("Titulo");
		
		String title = instance.getTitle();
		
		assertEquals( "Titulo", title );
	}
	

	@Test
	public void testEncontrarLinkDoPost() {
		PostPageElement instance = new PostPageElement(webElement);
		PageElement linkDoTitulo = mock(PageElement.class);
		when(webElement.findByCssSelector("a[data-event-action='title']")).thenReturn(Optional.of(linkDoTitulo));
		when(linkDoTitulo.getAttribute(stringCaptor.capture())).thenReturn("https://link.com");
		
		Link link = instance.getSelfLink();
		
		assertEquals( "https://link.com" , link.getHref() );
		assertEquals( "href", stringCaptor.getValue() );
	}
	
	@Test
	public void testEncontrarLinkDosComentarios() {
		
		PostPageElement instance = new PostPageElement(webElement);
		PageElement linkDosComentarios = mock(PageElement.class);
		when(webElement.findByCssSelector(".comments")).thenReturn(Optional.of(linkDosComentarios));
		when(linkDosComentarios.getAttribute(stringCaptor.capture())).thenReturn("https://comments.link.com");
		
		Link link = instance.getCommentsLink();
		
		assertEquals( "https://comments.link.com" , link.getHref() );
		assertEquals( "href", stringCaptor.getValue() );
	}
	

	@Test
	public void testQuandoPostNaoTemLinkDosComentarios() {
		
		PostPageElement instance = new PostPageElement(webElement);
		when(webElement.findByCssSelector(".comments")).thenReturn(Optional.empty());
		
		Link link = instance.getCommentsLink();
		
		assertEquals( "#" , link.getHref() );
	}
}
