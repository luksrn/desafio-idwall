package co.idwall.crawler.common;


public interface PageElement extends PageSearchContext {

    void click();

    String getAttribute(String attr);

    String getText();

    Object getDelegate();

}
