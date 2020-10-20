package by.jsf.example.service;

import by.jsf.example.entity.Link;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LinkService {

    //метод проверяет корректность ссылки
    public Boolean isLinkCorrect(String link) {
        UrlValidator validator = new UrlValidator();
        return validator.isValid(link);
    }

    //метод ищет и помещает в контейнер ссылки
    public List<Link> findLinks(String url) throws IOException {

        int counter = 1;
        String link;
        List<Link> links = new ArrayList<>();

        Document doc = Jsoup.connect(url) //подключаяемся
                .data("query", "Java")
                .userAgent("Mozilla")
                .cookie("auth", "token")
                .timeout(3000)
                .get();

        Elements elements = doc.select("a[href]"); //получаем все ссылки на странице
        for (Element element : elements) {
            link = element.attr("href");
            if (isLinkCorrect(link)) {
                links.add(new Link(counter, element.text(), element.attr("href"))); //помещаем ссылку в контейнер
                counter++;
            }
        }

        return links;

    }

}
