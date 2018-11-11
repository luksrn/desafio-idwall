package co.idwall.crawler.jsoup;

import co.idwall.crawler.common.PageElement;
import org.jsoup.nodes.Element;

public class JsoupPageElement extends JsoupPageSearchContext implements PageElement {


    public JsoupPageElement(Element e, JsoupWebDriver jsoupWebDriver){
        super(e,jsoupWebDriver);
    }

    @Override
    public void click() {
        String link = element.attr("href");
        jsoupWebDriver.get(link);
    }

    @Override
    public String getAttribute(String attr) {
        return element.attr(attr);
    }

    @Override
    public String getText() {
        return element.text();
    }

    @Override
    public Object getDelegate() {
        return element;
    }
}
