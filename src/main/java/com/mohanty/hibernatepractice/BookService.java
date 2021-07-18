package com.mohanty.hibernatepractice;

import com.mohanty.hibernatepractice.model.Book;
import com.mohanty.hibernatepractice.model.Chapter;
import com.mohanty.hibernatepractice.model.Publisher;
import com.mohanty.hibernatepractice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public void storeBook() {

        //Persisting object graph:
        Publisher publisher = new Publisher("MANN", "Manning Publishing Co.");
        Book book = new Book("983050804499201", "Java Persistance API in depth", publisher);

        Chapter chapter1 = new Chapter("Introduction JPA & Hibernate", 1);
        Chapter chapter2 = new Chapter("Domain Objects & metadata", 2);
        List<Chapter> chapters = new ArrayList<>();
        chapters.add(chapter1);
        chapters.add(chapter2);

        book.setChapters(chapters);
        repository.persistObjectGraph(book);
    }

}
