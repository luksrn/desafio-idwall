package co.idwall.crawler.reddit;

public class SubRedditPost {

	private Long upvotes;
	 
	private String title;
	 
	private Link self;
	
	private Link comments;

	public SubRedditPost(Long upvotes, String title, Link self, Link comments) {
		this.upvotes = upvotes;
		this.title = title;
		this.self = self;
		this.comments = comments;
	}

	public Long getUpvotes() {
		return upvotes;
	}

	public String getTitle() {
		return title;
	}

	public Link getSelf() {
		return self;
	}

	public Link getComments() {
		return comments;
	}
}
