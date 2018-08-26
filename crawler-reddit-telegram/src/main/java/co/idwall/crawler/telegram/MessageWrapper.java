package co.idwall.crawler.telegram;

import org.telegram.telegrambots.api.objects.Message;

public class MessageWrapper {

	private Message delegate;
	
	public MessageWrapper(Message delegate) {
		super();
		this.delegate = delegate;
	}
	
	public String getText() {
		return delegate.getText();
	}
	
	public Long getChatId() {
		return delegate.getChatId();
	}
	
	public String getUserFirstName() {
		return delegate.getFrom().getFirstName();
	}

	public boolean isCommand(String command) {
		String originalText = delegate.getText();
		return originalText != null && originalText.trim().startsWith(command);
	}
	
	public Message getDelegate() {
		return delegate;
	}
}
