package by.jsf.example;

import by.jsf.example.entity.Link;
import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
//		try {
//			findLinks("https://tut.by");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
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

	private Boolean isLinkCorrect(String link) {
		UrlValidator validator = new UrlValidator();
		return validator.isValid(link);
	}

	private void findLinks(String url) throws IOException {

		int counter = 1;
		String link;

		Document doc = Jsoup.connect(url)
				.data("query", "Java")
				.userAgent("Mozilla")
				.cookie("auth", "token")
				.timeout(3000)
				.get();

		Elements elements = doc.select("a[href]");
		for (Element element : elements) {
			link = element.attr("href");
			if (isLinkCorrect(link)) {
				links.add(new Link(counter, element.text(), element.attr("href")));
				counter++;
			}
		}

	}

	public void bAnalyzeClick() {
		try {
			links = new ArrayList<>();
			if (isLinkCorrect(linkText)) {
				findLinks(linkText);
			} else {
				showMessage();
			}

		} catch (IOException e) {
			showMessage();
			e.printStackTrace();
		}
	}

	public void bClearClick() {
		setLinks(new ArrayList<>());
	}

	public void linkAction(String linkAddress) {
		setLinkText(linkAddress);
	}

	public void showMessage() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!", "¬веден неверный URL"));
	}

//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}


}
