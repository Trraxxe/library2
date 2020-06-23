package ua.nure.kaverin.library.repository;

import ua.nure.kaverin.library.model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> getAllBooks();
}
