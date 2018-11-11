package co.idwall.crawler.reddit.crawlers;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import co.idwall.crawler.reddit.pages.SubRedditPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.idwall.crawler.reddit.SubRedditPost;
import co.idwall.crawler.reddit.pages.HeaderSubRedditPageElement;
import co.idwall.crawler.reddit.pages.PostPageElement;

public class SubRedditPageCrawlerTest {
	
	@Mock
    SubRedditPage subRedditPage;
	
	@Mock
	HeaderSubRedditPageElement headerRedditPage;
	
	@Captor
	ArgumentCaptor<Predicate<PostPageElement>> testCaptor;
	
	@Before
	public void init(){
    	MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testBuscaPontuacaoSemResultados() {
		
		when(subRedditPage.header()).thenReturn(headerRedditPage);
		when(subRedditPage.findPostsAtCurrentPageBy(any())).thenReturn(Collections.emptyList());
		
		SubRedditPageCrawler crawler = new SubRedditPageCrawler(subRedditPage);
		
		List<SubRedditPost> posts = crawler.findSubRedditTopicsBy(10_000_000L).getPosts();
		
		Assert.assertEquals( 0, posts.size() );
		//then
		verify(subRedditPage, times(1)).go();
		verify(subRedditPage, times(1)).header();
		verify(headerRedditPage, times(1)).clickTop();
		verify(subRedditPage,times(1)).findPostsAtCurrentPageBy(any());
		
	}
	
	
	@Test
	public void testBuscaPontuacaoComResultadosApenasNaPrimeiraPagina() {
		
		PostPageElement[] postsPageElements = { 
										mockPostPageElement(1_930L, false),
										mockPostPageElement(1_000L, false)
										};
										
		when(subRedditPage.header()).thenReturn(headerRedditPage);
		when(subRedditPage.findPostsAtCurrentPageBy(testCaptor.capture()))
					.thenReturn(Arrays.asList(postsPageElements ))
					.thenReturn(Collections.emptyList());
		
		SubRedditPageCrawler crawler = new SubRedditPageCrawler(subRedditPage);
		
		List<SubRedditPost> posts = crawler.findSubRedditTopicsBy(1_000L).getPosts();
		
		Assert.assertEquals( 2, posts.size() );
		//then
		verify(subRedditPage, times(1)).go();
		verify(subRedditPage, times(1)).header();
		verify(headerRedditPage, times(1)).clickTop();
		verify(subRedditPage,times(2)).findPostsAtCurrentPageBy(any());
		verify(subRedditPage, times(1)).nextPage();
		
		Predicate<PostPageElement> testsPosts = testCaptor.getValue();
		Assert.assertTrue(testsPosts.test( postsPageElements[0] ));
		Assert.assertTrue(testsPosts.test( postsPageElements[1] ));
	}
	
	
	@Test
	public void testBuscaPontuacaoComResultadosMaisDeUmaPagina() {

		//given
		PostPageElement[] postsPageUmElements = { 
										mockPostPageElement(1_930L, false),
										mockPostPageElement(1_800L, false)
		};
		
		PostPageElement[] postsPageDoisElements = { 
				mockPostPageElement(1_530L, false)
		};
										
		when(subRedditPage.header()).thenReturn(headerRedditPage);
		when(subRedditPage.findPostsAtCurrentPageBy(testCaptor.capture()))
					.thenReturn(Arrays.asList(postsPageUmElements))
					.thenReturn(Arrays.asList(postsPageDoisElements))
					.thenReturn(Collections.emptyList());
		
		//when
		SubRedditPageCrawler crawler = new SubRedditPageCrawler(subRedditPage);
		
		List<SubRedditPost> posts = crawler.findSubRedditTopicsBy(1_000L).getPosts();
		
		//then
		Assert.assertEquals( 3, posts.size() );
		verify(subRedditPage, times(1)).go();
		verify(subRedditPage, times(1)).header();
		verify(headerRedditPage, times(1)).clickTop();
		verify(subRedditPage,times(3)).findPostsAtCurrentPageBy(any());
		verify(subRedditPage, times(2)).nextPage();
		
		Predicate<PostPageElement> testsPosts = testCaptor.getValue();
		Assert.assertTrue(testsPosts.test( postsPageUmElements[0] ));
		Assert.assertTrue(testsPosts.test( postsPageUmElements[1] ));
		Assert.assertTrue(testsPosts.test( postsPageDoisElements[0] ));
	}
	
	private PostPageElement mockPostPageElement(Long upvotes, Boolean isPromoted) {
		PostPageElement post = mock(PostPageElement.class);
		when(post.isPromoted()).thenReturn(isPromoted);
		when(post.getUpvotes()).thenReturn(upvotes);
		return post;
	}
}
