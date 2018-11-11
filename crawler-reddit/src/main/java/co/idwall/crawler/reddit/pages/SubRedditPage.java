package co.idwall.crawler.reddit.pages;

import co.idwall.crawler.common.Page;
import co.idwall.crawler.common.PageElement;
import co.idwall.crawler.jsoup.JsoupPage;
import co.idwall.crawler.jsoup.JsoupWebDriver;
import co.idwall.crawler.selenium.SeleniumPage;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SubRedditPage {

	private Page page;
	private String subReddit;
	
	public SubRedditPage(WebDriver webDriver, String subReddit) {
		page = new SeleniumPage(webDriver, "https://old.reddit.com/r/" + subReddit);
		this.subReddit = subReddit;
	}

	public SubRedditPage(String subReddit) {
		page = new JsoupPage( new JsoupWebDriver(),"https://old.reddit.com/r/" + subReddit);
		this.subReddit = subReddit;
	}

	public void go(){
		page.go();
	}

	public HeaderSubRedditPageElement header() {
		return page.findByCssSelector("#header")
				.map(HeaderSubRedditPageElement::new)
				.get();
	}
	
	public List<PostPageElement> findPosts(){
		return page.findAllByCssSelector("div[data-subreddit]")
				.stream()				
				.map(PostPageElement::new)
				.collect(Collectors.toList());
	}
	
	public List<PostPageElement> findPostsAtCurrentPageBy(Predicate<PostPageElement> test){
		return findPosts().stream().filter(test).collect(Collectors.toList());
	}
	
	public void nextPage() {
		page.findByCssSelector(".next-button a").ifPresent(PageElement::click);
	}
	
	public String getSubReddit() {
		return subReddit;
	}
}
