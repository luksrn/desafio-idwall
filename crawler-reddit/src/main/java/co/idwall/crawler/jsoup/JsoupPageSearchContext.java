package co.idwall.crawler.jsoup;

import co.idwall.crawler.common.PageElement;
import co.idwall.crawler.common.PageSearchContext;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsoupPageSearchContext implements PageSearchContext {

    protected Element element;

    protected JsoupWebDriver jsoupWebDriver;

    public JsoupPageSearchContext(Element e, JsoupWebDriver jsoupWebDriver){
        this.element = e;
        this.jsoupWebDriver = jsoupWebDriver;
    }

    public Optional<PageElement> findByCssSelector(String cssSelector) {
            Elements elements = element.select(cssSelector);
            if(elements.isEmpty()){
                return Optional.empty();
            }
            if( elements.size() > 1 ){
                throw new IllegalArgumentException("No unique element found");
            }
            PageElement pageElement = new JsoupPageElement(elements.first(), jsoupWebDriver);
            return Optional.of(pageElement);
    }

    public List<PageElement> findAllByCssSelector(String cssSelector) {
        return element.select(cssSelector)
                .stream()
                .map( e -> new JsoupPageElement(e,jsoupWebDriver))
                .collect(Collectors.toList());
    }

}
