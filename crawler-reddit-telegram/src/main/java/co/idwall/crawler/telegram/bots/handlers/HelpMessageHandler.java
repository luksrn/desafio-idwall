package co.idwall.crawler.telegram.bots.handlers;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Message;

import co.idwall.crawler.telegram.MessageHandler;
import co.idwall.crawler.telegram.MessageWrapper;
import co.idwall.crawler.telegram.SendMessageEvent;

@Component
public class HelpMessageHandler implements MessageHandler {

	private ApplicationEventPublisher publisher;
	
	public HelpMessageHandler(ApplicationEventPublisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public boolean supports(MessageWrapper message) {
		return message.isCommand("/help") || message.isCommand("/start");
	}

	@Override
	public void process(MessageWrapper imessage) {
		Message message = imessage.getDelegate();
		publisher.publishEvent( new SendMessageEvent(message.getChatId() , 
        		"Olá, "  + message.getFrom().getFirstName()  + "! Eu sou um robô que pode te ocupar quando você estiver com <b>NadaPraFazer</b>\n" +
        		"Você pode me controlar enviando estes comandos:\n" + 
        		"\n" + 
        		"/help - <i>Exibe esta ajuda</i>\n" +
        		"/NadaPraFazer - <i>/NadaPraFazer programming;dogs;brazil</i>\n") );

	}
}
