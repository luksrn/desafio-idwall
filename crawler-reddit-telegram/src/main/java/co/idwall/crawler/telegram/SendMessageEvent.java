package co.idwall.crawler.telegram;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;

public class SendMessageEvent {

	private Long chatId;
	
	private Integer replyToMessageId;
	
	private String text;
	
	public SendMessageEvent(Long chatId, String text) {
		this(chatId, null, text);
	}
	
	public SendMessageEvent(Long chatId, Integer replayToMessageId, String text) {
		this.chatId = chatId;
		this.text = text;
		this.replyToMessageId = replayToMessageId;
	}
	
	public Long getChatId() {
		return chatId;
	}
	
	public Integer getReplyToMessageId() {
		return replyToMessageId;
	}
	
	public String getText() {
		return text;
	}
	
	public SendMessage getAsTelegramMessage() {

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId( chatId );
        sendMessage.enableHtml(true);
        sendMessage.setText(text);
        sendMessage.setReplyToMessageId( this.replyToMessageId );
        sendMessage.disableWebPagePreview();
        
        ReplyKeyboardRemove replyKeyboardRemove = new ReplyKeyboardRemove();
        replyKeyboardRemove.setSelective(true);
        sendMessage.setReplyMarkup(replyKeyboardRemove);
        
        return sendMessage;
	}
}
