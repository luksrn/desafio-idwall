package co.idwall.crawler.jsoup;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsoupWebDriver {

    private Document currentPage;

    private Map<String, String> cookies = new HashMap<>();

    private List<PageChangeListener> listeners = new LinkedList<>();

    public JsoupWebDriver(){}

    public JsoupWebDriver get(String page){
        Connection.Response response;
        try {
            response = Jsoup.connect(page)
                    .cookies(cookies)
                    .method(Connection.Method.GET)
                    .execute();

            cookies = response.cookies();
            currentPage = response.parse();

            publishEventPageChange();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
        return this;
    }

    public Document getCurrentPage() {
        return currentPage;
    }

    public void registerListener(PageChangeListener listener){
        listeners.add(listener);
    }

    public void publishEventPageChange(){
        for (PageChangeListener listener : listeners) {
            listener.onPageChange(new PageChangeEvent(currentPage));
        }
    }
}
