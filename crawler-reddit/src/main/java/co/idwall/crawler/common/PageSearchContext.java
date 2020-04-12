package co.idwall.crawler.common;


import java.util.List;
import java.util.Optional;

public interface PageSearchContext {

    Optional<PageElement> findByCssSelector(String cssSelector);

    List<PageElement> findAllByCssSelector(String cssSelector);
}
