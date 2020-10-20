package by.jsf.example;

import by.jsf.example.entity.Link;
import by.jsf.example.service.LinkService;
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
	private final LinkService linkService;

	public MainBean() {

		super();
		this.linkService = new LinkService();

	}

	@PostConstruct
	public void init() {

	}

	//метод используется для чтения адреса ссылки в поле ввода в браузере
	public String getLinkText() {
		return linkText;
	}

	//метод используется для заполнения таблицы ссылок в браузере
	public List<Link> getLinks() {
		return links;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	//метод обработки клика по кнопке Анализировать
	public void bAnalyzeClick() {
		try {
			if (linkService.isLinkCorrect(linkText)) { //проверяем корректность ссылки
				links = linkService.findLinks(linkText); //получаем список ссылок
			} else {
				showMessage(); //выводим сообщение об ошибке в браузер
			}
		} catch (IOException e) {
			showMessage(); //выводим сообщение об ошибке в браузер
			e.printStackTrace();
		}
	}

	//метод обработки клика по кнопке Очистить
	public void bClearClick() {
		setLinks(new ArrayList<>());
	}

	//метод обработки клика по ссылке в таблице
	public void linkAction(String linkAddress) {
		setLinkText(linkAddress); //устанавливаем адрес нажатой ссылки в поле ввода
	}

	//метод для вывода сообщения об ошибке в браузер
	public void showMessage() {
		FacesContext.getCurrentInstance().addMessage(null
				, new FacesMessage(FacesMessage.SEVERITY_ERROR
				, "Error!", "Введен неверный URL"));
	}

}
