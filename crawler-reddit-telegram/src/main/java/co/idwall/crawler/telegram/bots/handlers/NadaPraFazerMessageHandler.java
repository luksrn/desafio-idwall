package co.idwall.crawler.telegram.bots.handlers;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import co.idwall.crawler.reddit.SubRedditPostSearchResult;
import co.idwall.crawler.telegram.MessageHandler;
import co.idwall.crawler.telegram.MessageWrapper;
import co.idwall.crawler.telegram.SendMessageEvent;

@Component
public class NadaPraFazerMessageHandler implements MessageHandler {
	
	private NadaPraFazerSubRedditFetcher subRedditFetcher;
	
	private ApplicationEventPublisher publisher;
	
	public NadaPraFazerMessageHandler(NadaPraFazerSubRedditFetcher subRedditFetcher, ApplicationEventPublisher publisher) {
		super();
		this.subRedditFetcher = subRedditFetcher;
		this.publisher = publisher;
	}

	@Override
	public boolean supports(MessageWrapper originalMessage) {
		return originalMessage.isCommand("/NadaPraFazer");
	}

	@Override
	public void process(MessageWrapper originalMessage) {
		

		String subRedditsByComma = originalMessage.getText()
										.replaceAll("/NadaPraFazer", "")
										.trim();

		publisher.publishEvent( new SendMessageEvent( originalMessage.getChatId(), "Ok, vou procurar os posts em " + subRedditsByComma + "! Aguarde um momento...") );
				
		subRedditFetcher.search(subRedditsByComma, (subRedditPostSearchResult) -> {
			String replyMessage = convertToMessage(subRedditPostSearchResult, originalMessage);
    		replyToUser(replyMessage,originalMessage);
		});
	}
		
	private String convertToMessage(SubRedditPostSearchResult sr, MessageWrapper originalMessage) {
		StringBuilder builder = new StringBuilder();
		
		if( sr.getPosts().isEmpty() ) {
			builder.append("Opa, " + originalMessage.getUserFirstName() + " eu não encontrei nada de interessante em <b>" + sr.getSubReddit() + "</b>\n");
		} else {
			builder.append("Opa, " + originalMessage.getUserFirstName() + " eu encontrei os seguintes posts em <b>" + sr.getSubReddit() + "</b>\n");
			sr.getPosts().forEach( p -> {					;
				builder.append("\n<b>Upvotes:</b> " + p.getUpvotes() );			
				builder.append("\n<b>Título:</b> " + p.getTitle() );				
				builder.append("\n<a href=\"" + p.getSelf().getHref() + "\">Link para Thread</a>" );
				builder.append("\n<a href=\"" + p.getComments().getHref() + "\">Link para comentários</a>" );
				builder.append("\n");
			});
		}
		return builder.toString();
	}	
	
	private void replyToUser(String message, MessageWrapper originalMessage) {
		publisher.publishEvent( new SendMessageEvent( originalMessage.getChatId(), originalMessage.getDelegate().getMessageId(), message ) );	
	}
	
}
