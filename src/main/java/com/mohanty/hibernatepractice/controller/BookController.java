package com.mohanty.hibernatepractice.controller;

import com.mohanty.hibernatepractice.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookService service;

    @GetMapping("/store")
    public void storeBook() {
        service.storeBook();
    }

}
