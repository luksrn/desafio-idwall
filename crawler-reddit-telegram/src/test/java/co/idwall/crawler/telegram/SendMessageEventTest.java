package co.idwall.crawler.telegram;


import org.junit.Assert;
import org.junit.Test;
import org.telegram.telegrambots.api.methods.send.SendMessage;

public class SendMessageEventTest {

	@Test
	public void testSimpleMessage() {
		SendMessageEvent event = new SendMessageEvent(10L, "Olá");
		
		Assert.assertEquals( 10L, (long) event.getChatId() );
		Assert.assertEquals( "Olá", event.getText() );
		Assert.assertNull( event.getReplyToMessageId() );
		
		SendMessage telegramMessage = event.getAsTelegramMessage();
		
		Assert.assertEquals( 10L, Long.parseLong(telegramMessage.getChatId()) );
		Assert.assertEquals( "Olá", telegramMessage.getText() );
		Assert.assertTrue( telegramMessage.getDisableWebPagePreview() );
		Assert.assertNull( telegramMessage.getReplyToMessageId() );
	}
	
	@Test
	public void testSimpleReplyMessage() {
		SendMessageEvent event = new SendMessageEvent(120L, 5, "Isto é um reply!");
		
		Assert.assertEquals( 120L, (long) event.getChatId() );
		Assert.assertEquals( "Isto é um reply!", event.getText() );
		Assert.assertEquals( 5, (int) event.getReplyToMessageId() );
		
		SendMessage telegramMessage = event.getAsTelegramMessage();
		
		Assert.assertEquals( 120L, Long.parseLong(telegramMessage.getChatId()) );
		Assert.assertEquals( "Isto é um reply!", telegramMessage.getText() );
		Assert.assertTrue( telegramMessage.getDisableWebPagePreview() );
		Assert.assertEquals( 5, (int) telegramMessage.getReplyToMessageId() );
	}

}
