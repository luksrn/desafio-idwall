package co.idwall.crawler.selenium;

import org.junit.Assert;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

@Disabled
class RemoteWebDriverProviderTest {

	@Test
	void testSeImplementacaoPadraoEhUmWebDriverRemotoComFirefox() {
		RemoteWebDriverProvider defaultImpl = new RemoteWebDriverProvider();
		
		WebDriver seleniumRemoteWebDriver = defaultImpl.get();
		
		Assert.assertTrue( seleniumRemoteWebDriver instanceof RemoteWebDriver );
		Assert.assertEquals("firefox", ((RemoteWebDriver) seleniumRemoteWebDriver).getCapabilities().getBrowserName() );
	}
}
