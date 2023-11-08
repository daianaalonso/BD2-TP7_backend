package ar.unrn.tp.domain;

import java.time.LocalDate;

public class Page {
    private String id;
    private String title;
    private String text;
    private String author;
    private LocalDate date;

    public Page(String title, String text, String author, LocalDate date) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
