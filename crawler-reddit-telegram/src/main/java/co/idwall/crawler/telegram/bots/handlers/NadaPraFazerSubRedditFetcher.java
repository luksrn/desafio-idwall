package co.idwall.crawler.telegram.bots.handlers;

import java.util.Arrays;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import co.idwall.crawler.reddit.SubRedditPostSearchResult;
import co.idwall.crawler.reddit.crawlers.SubRedditPageCrawler;
import co.idwall.crawler.reddit.pages.SubRedditPage;
import co.idwall.crawler.selenium.WebDriverProvider;

@Component
public class NadaPraFazerSubRedditFetcher {
	
	@Value("${crawler.subreddit.upvote}")
	private Long upvote;
	
	@Autowired
	private WebDriverProvider webDriverProvider;
	
	public void search(String subRedditsByComma, Consumer<SubRedditPostSearchResult> consumerSubRedditResult ) {
	    Arrays.asList(subRedditsByComma.split(";"))
				.parallelStream()
	        	.map( subReddit -> new SubRedditPage(webDriverProvider.get(), subReddit) )
	        	.map(SubRedditPageCrawler::new)
	        	.map( crawler -> crawler.findSubRedditTopicsBy(upvote) )
	        	.forEach(consumerSubRedditResult::accept);
	}
}
