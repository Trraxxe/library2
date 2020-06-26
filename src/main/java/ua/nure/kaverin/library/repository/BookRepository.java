package ua.nure.kaverin.library.repository;

import ua.nure.kaverin.library.model.Book;

import java.util.List;

public interface BookRepository {

    List<Book> getAllBooks();

    void createBook(Book book);

    default void updateBook(Book book) {

    }

    default Book getBookById(String id) {
        return null;
    }

    default void deleteBook(String id) {

    }
}
