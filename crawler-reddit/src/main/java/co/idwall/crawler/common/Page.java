package co.idwall.crawler.common;

public interface Page extends PageSearchContext {

    void go();

    String getUrl();
}
