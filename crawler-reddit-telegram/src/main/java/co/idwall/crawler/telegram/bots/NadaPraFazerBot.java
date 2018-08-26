package co.idwall.crawler.telegram.bots;


import java.util.List;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

import co.idwall.crawler.properties.BotProperties;
import co.idwall.crawler.telegram.MessageHandler;
import co.idwall.crawler.telegram.MessageWrapper;
import co.idwall.crawler.telegram.SendMessageEvent;

@Component
@EnableConfigurationProperties(BotProperties.class)
public class NadaPraFazerBot extends TelegramLongPollingBot {

	private BotProperties properties;
	
	private List<MessageHandler> messageHandlers;
	
	public NadaPraFazerBot(BotProperties properties, List<MessageHandler> messageHandlers) {
		this.properties = properties;
		this.messageHandlers = messageHandlers;
	}
	
	@Override
	public String getBotUsername() {
		return properties.getName();
	}
	 
	@Override
	public String getBotToken() {
		return properties.getToken();
	}
	
	@Override
	public void onUpdateReceived(Update update) {
		MessageWrapper message = new MessageWrapper( update.getMessage() );
		messageHandlers.stream()
			.filter( h -> h.supports(message))
			.findFirst()
			.ifPresent( h -> h.process(message) );
				        
	}

	@EventListener
	public void onSendMessageEvent(SendMessageEvent event) {
		try {
			sendApiMethod(event.getAsTelegramMessage());
		} catch (TelegramApiException e) {
			BotLogger.error(NadaPraFazerBot.class.getName(), e);
		}
	}
}
