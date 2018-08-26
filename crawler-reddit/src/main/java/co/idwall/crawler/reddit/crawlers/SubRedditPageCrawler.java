package co.idwall.crawler.reddit.crawlers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import co.idwall.crawler.reddit.SubRedditPost;
import co.idwall.crawler.reddit.SubRedditPostSearchResult;
import co.idwall.crawler.reddit.pages.PostPageElement;
import co.idwall.crawler.reddit.pages.SubRedditPage;

public class SubRedditPageCrawler {
	
	private SubRedditPage subRedditPage;
	
	public SubRedditPageCrawler(SubRedditPage page) {
		this.subRedditPage = page;
	}

	public SubRedditPostSearchResult findSubRedditTopicsBy(Long upvote) {
		
		subRedditPage.go();
		
		subRedditPage.header().clickTop();
		
		List<SubRedditPost> posts = new ArrayList<>();
		int delta = posts.size();
		do {
			subRedditPage.findPostsAtCurrentPageBy( pe -> pe.getUpvotes() >= upvote && !pe.isPromoted() )
				.stream()
				.map(PostPageElement::toPost)
				.collect(Collectors.toCollection(() -> posts));
			
			if(delta == posts.size() ) {
				break;
			} else {
				delta = posts.size();
				subRedditPage.nextPage();
			}
		} while (true);
		
		return new SubRedditPostSearchResult(subRedditPage.getSubReddit(), posts);
	}
}
