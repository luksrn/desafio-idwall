package co.idwall.crawler.telegram.bot.handlers;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationEventPublisher;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;

import co.idwall.crawler.telegram.MessageWrapper;
import co.idwall.crawler.telegram.SendMessageEvent;
import co.idwall.crawler.telegram.bots.handlers.HelpMessageHandler;

public class HelpMessageHandlerTest {
	
	HelpMessageHandler instance;
	
	@Mock
	ApplicationEventPublisher publisher;
	
	@Captor
	ArgumentCaptor<SendMessageEvent> messageCaptor;
	
    @Before
	public void init(){
    	MockitoAnnotations.initMocks(this);
    	instance = new HelpMessageHandler(publisher);
	}
	
	@Test
	public void testVerificacaoMensagemNull() {
		Message message = mock(Message.class);
		when(message.getText()).thenReturn(null);
		
		Assert.assertFalse( instance.supports( new MessageWrapper(message)) );
	}

	@Test
	public void testVerificacaoMensagemTexto() {
		Message message = mock(Message.class);
		when(message.getText()).thenReturn("Olá");
		
		Assert.assertFalse( instance.supports( new MessageWrapper(message)) );
	}
	

	@Test
	public void testVerificacaoMensagemDeAjuda() {
		Message message = mock(Message.class);
		when(message.getText()).thenReturn("/help");
		
		Assert.assertTrue( instance.supports( new MessageWrapper(message)) );
	}
	
	@Test
	public void testVerificacaoMensagemDaInicializacaoDoBot() {
		Message message = mock(Message.class);
		when(message.getText()).thenReturn("/start ");
		
		Assert.assertTrue( instance.supports( new MessageWrapper(message) ) );
	}
	

	@Test
	public void testVerificacaoMensagemDeAjudaComEspaco() {
		Message message = mock(Message.class);
		when(message.getText()).thenReturn("  /help ");
		
		Assert.assertTrue( instance.supports( new MessageWrapper(message) ) );
	}
	
	@Test
	public void testMensagemDeAjudaEntregueAoDestinatario() {
		User user = mock(User.class);
		Message message = mock(Message.class);
		
		when(user.getFirstName()).thenReturn("Lucas");
		when(message.getFrom()).thenReturn( user );
		when(message.getChatId()).thenReturn(100L);
		
		instance.process(new MessageWrapper(message));
		
		verify(publisher, atLeastOnce()).publishEvent( messageCaptor.capture() );
		SendMessageEvent messagePublished = messageCaptor.getValue();
		Assert.assertEquals( (long)100L,(long) messagePublished.getChatId());
		Assert.assertEquals(  
        		"Olá, Lucas! Eu sou um robô que pode te ocupar quando você estiver com <b>NadaPraFazer</b>\n" +
        		"Você pode me controlar enviando estes comandos:\n" + 
        		"\n" + 
        		"/help - <i>Exibe esta ajuda</i>\n" +
        		"/NadaPraFazer - <i>/NadaPraFazer programming;dogs;brazil</i>\n", messagePublished.getText() );
	}


}
