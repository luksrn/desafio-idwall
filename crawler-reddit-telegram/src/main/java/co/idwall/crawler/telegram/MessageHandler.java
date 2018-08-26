package co.idwall.crawler.telegram;

public interface MessageHandler {

	boolean supports(MessageWrapper message);
	
	void process(MessageWrapper message);
}
