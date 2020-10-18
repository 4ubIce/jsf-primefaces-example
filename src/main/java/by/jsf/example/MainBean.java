package by.jsf.example;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

import by.jsf.example.entity.Link;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "mainBean")
public class MainBean implements Serializable {

	private String linkText;
	private List<Link> links = new ArrayList<>();

	public MainBean() {
		super();
//		try {
//			for (String link : findLinks("https://tut.by")) {
//				System.out.println(link);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	@PostConstruct
	public void init() {
		try {
			findLinks("https://tut.by");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getLinkText() {
		return linkText;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	private void findLinks(String url) throws IOException {

		int counter = 1;

		Document doc = Jsoup.connect(url)
				.data("query", "Java")
				.userAgent("Mozilla")
				.cookie("auth", "token")
				.timeout(3000)
				.get();

		Elements elements = doc.select("a[href]");
		for (Element element : elements) {
			links.add(new Link(counter, element.text(), element.attr("href")));
			counter++;
		}

	}

	public void bAnalyzeClick() {
		try {
			findLinks(linkText);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(links.size());
	}

	public void bClearClick() {
		links = new ArrayList<>();
	}


//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}


}
