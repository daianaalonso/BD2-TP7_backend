package ar.unrn.tp.domain;

import java.time.LocalDate;
import java.util.List;

public class Post {
    private Long id;
    private String title;
    private String text;
    private List<String> tags;
    private String resume;
    private List<String> relatedLinks;
    private String author;
    private LocalDate date;

    public Post(String title, String text, List<String> tags, String resume, List<String> relatedLinks, String author, LocalDate date) {
        this.title = title;
        this.text = text;
        this.tags = tags;
        this.resume = resume;
        this.relatedLinks = relatedLinks;
        this.author = author;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public List<String> getRelatedLinks() {
        return relatedLinks;
    }

    public void setRelatedLinks(List<String> relatedLinks) {
        this.relatedLinks = relatedLinks;
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
