package com.example.vts.base;

public class Book {

    public Book(String title, String author, Integer price, String description, String uriSegment, String theme, String publisher, String mssidn) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
        this.uriSegment = uriSegment;
        this.theme = theme;
        this.publisher = publisher;
        this.mssidn = mssidn;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUriSegment() {
        return uriSegment;
    }

    public void setUriSegment(String uriSegment) {
        this.uriSegment = uriSegment;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getMssidn() {
        return mssidn;
    }

    public void setMssidn(String mssidn) {
        this.mssidn = mssidn;
    }

    private String author;
    private Integer price;
    private String description;
    private String uriSegment;
    private String theme;
    private String publisher;
    private String mssidn;
}
