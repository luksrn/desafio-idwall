package co.idwall.crawler;

import java.util.List;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.generics.LongPollingBot;

import co.idwall.crawler.selenium.RemoteWebDriverProvider;
import co.idwall.crawler.selenium.WebDriverProvider;

@SpringBootApplication
public class Application {
	
	@Configuration
	public static class ConfigSelenium {
		
		@Value("${selenium.url}")
		private String urlSelenium;
		
		@Bean
		public WebDriverProvider webDriverProvider() {
			return new RemoteWebDriverProvider(urlSelenium, DesiredCapabilities.firefox());
		}
	}

	@Configuration
	public static class ConfigTelegram {
		@Bean
		public TelegramBotsApi telegramBotsApi() {
	        return new TelegramBotsApi();
	    }
		
		@Bean
		public InitializingBean initialize(TelegramBotsApi telegramBotsApi, List<LongPollingBot> bots) {
			return () ->  
				bots.forEach( f -> {
					try { telegramBotsApi.registerBot(f); } catch (Exception e) {e.printStackTrace();}
				});
		}
	}
	
	public static void main(String[] args) {
		ApiContextInitializer.init();
		SpringApplication.run(Application.class, args);
	}
}
