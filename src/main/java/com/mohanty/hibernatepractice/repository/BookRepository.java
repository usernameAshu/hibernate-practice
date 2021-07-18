package com.mohanty.hibernatepractice.repository;

import com.mohanty.hibernatepractice.model.Book;
import com.mohanty.hibernatepractice.model.Chapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class BookRepository {

    private Connection connection = null;
    private String driverClassName = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/bookstore";
    private String username = "root";
    private String password = "password";
    private static final Logger LOGGER = LoggerFactory.getLogger(BookRepository.class);

    public void persistObjectGraph(Book book) {

        try {
            Class.forName(driverClassName);
            connection = DriverManager.getConnection(url, username, password);

            //insert data into publisher table
            String sql = "INSERT INTO bookstore.PUBLISHER (CODE, PUBLISHER_NAME) VALUES(?,?)";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, book
                    .getPublisher()
                    .getCode());
            stmt.setString(2, book
                    .getPublisher()
                    .getPublisherName());

            LOGGER.info(sql+"\n Parameters: "+book.getPublisher().getCode()+","+book.getPublisher().getPublisherName());
            stmt.executeUpdate();
            stmt.close();

            //insert into book table
            sql = "INSERT INTO bookstore.BOOK (ISBN, BOOK_NAME, PUBISHER_CODE) VALUES(?, ?, ?)";
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, book.getIsbn());
            stmt.setString(2, book.getBookName());
            stmt.setString(3, book
                    .getPublisher()
                    .getCode());

            LOGGER.info(sql+"\n Parameters: "+book.getIsbn()+","+book.getBookName()+","+book.getPublisher().getCode());
            stmt.executeUpdate();
            stmt.close();

            //insert into chapters table
            sql = "INSERT INTO bookstore.CHAPTER (BOOK_ISBN, CHAPTER_NUM, TITLE) VALUES(?, ?,?)";
            stmt = connection.prepareStatement(sql);
            for (Chapter chapter : book.getChapters()) {
                stmt.setString(1, book.getIsbn());
                stmt.setInt(2, chapter.getChapterNumber());
                stmt.setString(3, chapter.getTitle());
                LOGGER.info(sql+"\n Parameters: "+book.getIsbn()+","+chapter.getChapterNumber()+","+chapter.getTitle());
                stmt.executeUpdate();
            }
            stmt.close();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }

    }

}

