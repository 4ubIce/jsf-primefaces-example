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

	//����� ������������ ��� ������ ������ ������ � ���� ����� � ��������
	public String getLinkText() {
		return linkText;
	}

	//����� ������������ ��� ���������� ������� ������ � ��������
	public List<Link> getLinks() {
		return links;
	}

	public void setLinkText(String linkText) {
		this.linkText = linkText;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	//����� ��������� ����� �� ������ �������������
	public void bAnalyzeClick() {
		try {
			if (linkService.isLinkCorrect(linkText)) { //��������� ������������ ������
				links = linkService.findLinks(linkText); //�������� ������ ������
			} else {
				showMessage(); //������� ��������� �� ������ � �������
			}
		} catch (IOException e) {
			showMessage(); //������� ��������� �� ������ � �������
			e.printStackTrace();
		}
	}

	//����� ��������� ����� �� ������ ��������
	public void bClearClick() {
		setLinks(new ArrayList<>());
	}

	//����� ��������� ����� �� ������ � �������
	public void linkAction(String linkAddress) {
		setLinkText(linkAddress); //������������� ����� ������� ������ � ���� �����
	}

	//����� ��� ������ ��������� �� ������ � �������
	public void showMessage() {
		FacesContext.getCurrentInstance().addMessage(null
				, new FacesMessage(FacesMessage.SEVERITY_ERROR
				, "Error!", "������ �������� URL"));
	}

}
