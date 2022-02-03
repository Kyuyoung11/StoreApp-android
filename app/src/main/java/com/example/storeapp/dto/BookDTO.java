package com.example.storeapp.dto;

public class BookDTO {

    Long id;
    String url;
    String name;
    int price;
    String writer;
    String company;
    String detail;



    public BookDTO(Long id, String url,String name, int price,String writer, String company, String detail) {

        this.id = id;
        this.name = name;
        this.writer = writer;
        this.company = company;
        this.url = url;
        this.price = price;
        this.detail = detail;
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

    public String getPrice() {
        return Integer.toString(price);
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
