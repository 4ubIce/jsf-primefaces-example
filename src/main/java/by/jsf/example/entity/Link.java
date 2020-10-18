package by.jsf.example.entity;

import java.io.Serializable;
import java.util.Objects;

public class Link implements Serializable {

    public int id;
    public String text;
    public String value;

    public Link() {

    }

    public Link(int id, String text, String value) {
        this.id = id;
        this.text = text;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link = (Link) o;
        return id == link.id &&
                Objects.equals(text, link.text) &&
                value.equals(link.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, value);
    }
}
