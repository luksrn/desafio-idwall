package co.idwall.crawler.telegram.bot.handlers;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.telegram.telegrambots.api.objects.Message;

import co.idwall.crawler.telegram.MessageWrapper;
import co.idwall.crawler.telegram.SendMessageEvent;
import co.idwall.crawler.telegram.bots.handlers.NadaPraFazerMessageHandler;
import co.idwall.crawler.telegram.bots.handlers.NadaPraFazerSubRedditFetcher;

public class NadaPraFazerMessageHandlerTest {

	NadaPraFazerMessageHandler instance;
	
	@Mock
	NadaPraFazerSubRedditFetcher nadaPraFazerFetcher;
	
	@Mock
	ApplicationEventPublisher publisher;
	
	@Captor
	ArgumentCaptor<SendMessageEvent> messageCaptor;
	
    @Before
	public void init(){
    	MockitoAnnotations.initMocks(this);
    	instance = new NadaPraFazerMessageHandler(nadaPraFazerFetcher,publisher);
	}
	
	@Test
	public void testVerificacaoMensagemNull() {
		Message message = mock(Message.class);
		when(message.getText()).thenReturn(null);
		
		Assert.assertFalse( instance.supports( new MessageWrapper(message)) );
	}
	

	@Test
	public void testVerificacaoMensagemDeNadaPraFazer() {
		Message message = mock(Message.class);
		when(message.getText()).thenReturn("/NadaPraFazer cats");
		
		Assert.assertTrue( instance.supports( new MessageWrapper(message)) );
	}
	
	@Test
	public void testVerificacaoMensagemDeNadaPraFazerSemSubreddits() {
		Message message = mock(Message.class);
		when(message.getText()).thenReturn("/NadaPraFazer");
		
		Assert.assertTrue( instance.supports( new MessageWrapper(message)) );
	}

	@Test
	@Ignore
	public void testMensagemDeAjudaEntregueAoDestinatario() {
		MessageWrapper message = mock(MessageWrapper.class);
		
		when(message.getUserFirstName()).thenReturn("Lucas");
		when(message.getText()).thenReturn( "/NadaPraFazer cats;dogs" );
		when(message.getChatId()).thenReturn(100L);
		
		instance.process(message);
		
		verify(publisher, Mockito.atLeast(2)).publishEvent( messageCaptor.capture() );
		SendMessageEvent messagePublished = messageCaptor.getAllValues().get(0);
		
		Assert.assertEquals( (long)100L,(long) messagePublished.getChatId());
		Assert.assertEquals(  "Ok, vou procurar os posts em cats;dogs! Aguarde um momento...", messagePublished.getText() );
	}
}
