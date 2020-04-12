package co.idwall.crawlerconsole;

import co.idwall.crawler.reddit.SubRedditPostSearchResult;
import co.idwall.crawler.reddit.crawlers.SubRedditPageCrawler;
import co.idwall.crawler.reddit.pages.SubRedditPage;
import co.idwall.crawler.selenium.WebDriverProvider;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Component
public class CrawlerConsoleCommandLineRunner implements CommandLineRunner {
	
	private static final Log LOGGER = LogFactory.getLog(CrawlerConsoleApplication.class);

	@Value("${crawler.reddit.upvotes}")
	private Long upvotes;
	
	@Value("${crawler.reddit.subreddits}")
	private String subreddits;

	@Autowired
	private WebDriverProvider webDriverProvider;

	@Override
	public void run(String... args) throws Exception {
		
		if(StringUtils.isEmpty(subreddits)) {
			LOGGER.fatal("Você deve especificar os subreddits! Ex: --crawler.reddit.subreddits=\"cats;askreddit\"");
			return;
		}
		LOGGER.info("Iniciando subreddits crawller...");
		LOGGER.info("\t * Upvotes = " + upvotes );
		LOGGER.info("\t * SubReddits = " + subreddits );

		try {
			Arrays.asList(subreddits.split(";"))
					.parallelStream()
					//.map( subReddit -> new SubRedditPage(webDriverProvider.get(), subReddit) )
					.map(subReddit -> new SubRedditPage(subReddit))
					.map(SubRedditPageCrawler::new)
					.map(crawler -> crawler.findSubRedditTopicsBy(this.upvotes))
					.forEach(this::print);
		} catch (Exception e){
			e.printStackTrace();
		}

        LOGGER.info("Finalizado.");
	}

	private void print(SubRedditPostSearchResult result) {
		if( result.getPosts().isEmpty() ) {
			System.out.println("Nada encontrado em " + result.getSubReddit() );
			return;
		}
		System.out.println("-----------------------------------");
		System.out.println("SubReddit = " + result.getSubReddit() );
		result.getPosts().forEach( p -> {
			System.out.println("Upvotes = " + p.getUpvotes() );
			System.out.println("Título da thread = " + p.getTitle() );				
			System.out.println("Link da Thread = " + p.getSelf().getHref() );
			System.out.println("Link para os comentários = " + p.getComments().getHref() );
			System.out.println("");
		});
	}
}
