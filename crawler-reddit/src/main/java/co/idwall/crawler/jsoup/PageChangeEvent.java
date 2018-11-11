package co.idwall.crawler.jsoup;

import org.jsoup.nodes.Document;

public class PageChangeEvent {

    private Document newPage;

    public PageChangeEvent(Document newPage) {
        this.newPage = newPage;
    }

    public Document getNewPage() {
        return newPage;
    }
}
