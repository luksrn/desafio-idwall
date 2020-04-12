package co.idwall.crawler.jsoup;

import co.idwall.crawler.common.Page;

public class JsoupPage extends JsoupPageSearchContext implements Page, PageChangeListener {

    private String url;

    public JsoupPage(JsoupWebDriver jsoupWebDriver, String url) {
        super(  jsoupWebDriver.get(url).getCurrentPage(), jsoupWebDriver );
        this.url = url;
        jsoupWebDriver.registerListener(this);
    }

    public void go() {
        jsoupWebDriver.get(url);
    }

    public String getUrl() {
        return url;
    }

    @Override
    public void onPageChange(PageChangeEvent e) {
        super.element = e.getNewPage();
    }
}
