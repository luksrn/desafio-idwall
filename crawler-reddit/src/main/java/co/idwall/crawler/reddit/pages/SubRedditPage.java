package co.idwall.crawler.reddit.pages;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import co.idwall.crawler.selenium.Page;

public class SubRedditPage extends Page {

	private String subReddit;
	
	public SubRedditPage(WebDriver webDriver, String subReddit) {
		super(webDriver, "https://old.reddit.com/r/" + subReddit);
		this.subReddit = subReddit;
	}
	
	public HeaderSubRedditPageElement header() {
		return findByCssSelector("#header")
				.map(HeaderSubRedditPageElement::new)
				.get();
	}
	
	public List<PostPageElement> findPosts(){
		return findAllByCssSelector("div[data-subreddit]")
				.stream()				
				.map(PostPageElement::new)
				.collect(Collectors.toList());
	}
	
	public List<PostPageElement> findPostsAtCurrentPageBy(Predicate<PostPageElement> test){
		return findPosts().stream().filter(test).collect(Collectors.toList());
	}
	
	public void nextPage() {
		findByCssSelector(".next-button a").ifPresent(WebElement::click);
	}
	
	public String getSubReddit() {
		return subReddit;
	}
}
