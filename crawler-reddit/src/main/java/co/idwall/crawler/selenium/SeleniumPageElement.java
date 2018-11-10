package co.idwall.crawler.selenium;

import co.idwall.crawler.common.PageElement;
import org.openqa.selenium.WebElement;

public class SeleniumPageElement extends SeleniumPageSearchContext implements  PageElement {

    private org.openqa.selenium.WebElement webElement;

    public SeleniumPageElement(PageElement pageElement) {
        this((WebElement)pageElement.getDelegate());
    }

    public SeleniumPageElement(WebElement webElement) {
        super(webElement);
        this.webElement = webElement;
    }

    @Override
    public Object getDelegate() {
        return webElement;
    }

    @Override
    public String getAttribute(String attr) {
        return webElement.getAttribute(attr);
    }

    @Override
    public String getText() {
        return webElement.getText();
    }

    @Override
    public void click() {
        webElement.click();
    }

}
