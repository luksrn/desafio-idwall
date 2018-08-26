package co.idwall.crawler.reddit;

import java.util.ArrayList;
import java.util.List;

public class SubRedditPostSearchResult {

	private String subReddit;
	 
	private List<SubRedditPost> posts;

	public SubRedditPostSearchResult(String subreddit) {
		this.subReddit = subreddit;
		this.posts = new ArrayList<>();
	}

	public SubRedditPostSearchResult(String subreddit, List<SubRedditPost> posts) {
		this.subReddit = subreddit;
		this.posts = posts;
	}
	
	public String getSubReddit() {
		return subReddit;
	}
	
	public List<SubRedditPost> getPosts() {
		return posts;
	}
}
