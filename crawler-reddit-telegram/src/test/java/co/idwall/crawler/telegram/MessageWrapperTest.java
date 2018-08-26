package co.idwall.crawler.telegram;


import static org.mockito.Mockito.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.User;

public class MessageWrapperTest {

	@Mock
	Message message;

	@Mock
	User user;
	
    @Before
	public void init(){
    	MockitoAnnotations.initMocks(this);
	}
    
	@Test
	public void testEncapsulamentoDaMensagemTelegram() {
		// when
		when(message.getChatId()).thenReturn(10L);
		when(message.getText()).thenReturn("Olá");
		when(message.getFrom()).thenReturn(user);
		when(user.getFirstName()).thenReturn("Lucas");
		
		MessageWrapper delegate = new MessageWrapper(message);
		
		Assert.assertEquals(10L, (long)delegate.getChatId());
		Assert.assertEquals("Olá", delegate.getText());
		Assert.assertEquals("Lucas", delegate.getUserFirstName() );
		Assert.assertFalse( delegate.isCommand("/help") );

		verify(message, times(1)).getChatId();
		verify(message, times(2)).getText();
		verify(message, times(1)).getFrom();
		verify(user, times(1)).getFirstName();
	}

	@Test
	public void testEncapsulamentoDeUmComando() {
		// when
		when(message.getChatId()).thenReturn(10L);
		when(message.getText()).thenReturn("/NadaPraFazer cats;askreddit");
		when(message.getFrom()).thenReturn(user);
		when(user.getFirstName()).thenReturn("Lucas Farias");
		
		MessageWrapper delegate = new MessageWrapper(message);
		
		Assert.assertEquals(10L, (long)delegate.getChatId());
		Assert.assertEquals("/NadaPraFazer cats;askreddit", delegate.getText());
		Assert.assertEquals("Lucas Farias", delegate.getUserFirstName() );
		Assert.assertTrue( delegate.isCommand("/NadaPraFazer") );

		verify(message, times(1)).getChatId();
		verify(message, times(2)).getText();
		verify(message, times(1)).getFrom();
		verify(user, times(1)).getFirstName();
	}
	
}
