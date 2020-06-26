package ua.nure.kaverin.library.service;

import ua.nure.kaverin.library.model.Book;

import java.util.List;

public interface BookService {

    List<Book> getAllBooks();

    void createBook(Book book);

    void updateBook(Book book);

    Book getBookById(String id);

    void deleteBook(String id);
}
