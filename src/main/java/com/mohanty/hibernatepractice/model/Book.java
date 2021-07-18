package com.mohanty.hibernatepractice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Book {
    private String isbn;
    private String bookName;
    private Publisher publisher;
    private List<Chapter> chapters;

    public Book(String isbn, String bookName, Publisher publisher) {
        this.isbn = isbn;
        this.bookName = bookName;
        this.publisher = publisher;
    }
}
