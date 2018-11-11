package co.idwall.crawlerconsole;

import co.idwall.crawler.selenium.WebDriverProvider;
import co.idwall.crawler.selenium.drivers.RemoteWebDriverProvider;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class CrawlerConsoleApplication {

/*	@Configuration
	static class Config {
		
		@Value("${selenium.url}")
		private String urlSelenium;
		
		@Bean
		public WebDriverProvider webDriverProvider() {
			return new RemoteWebDriverProvider(urlSelenium, DesiredCapabilities.firefox());
		}
	}*/
	
	public static void main(String[] args) {
		SpringApplication.run(CrawlerConsoleApplication.class, args);
	}
}
