package com.example.storeapp.dto;

public class BookDTO {

    String name;
    String writer;
    String company;
    String url;
    int price;

    public BookDTO(String name, String writer, String company, String url, int price) {
        this.name = name;
        this.writer = writer;
        this.company = company;
        this.url = url;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getWriter() {
        return writer;
    }

    public String getCompany() {
        return company;
    }

    public String getUrl() {
        return url;
    }

    public int getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
