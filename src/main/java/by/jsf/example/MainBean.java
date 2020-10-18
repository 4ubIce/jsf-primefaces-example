package by.jsf.example;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ViewScoped
@ManagedBean(name = "mainBean")
public class MainBean implements Serializable {

	private String	name	= "John";

	public MainBean() {
		super();
	}

	@PostConstruct
	public void init() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
