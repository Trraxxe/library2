package ua.nure.kaverin.library.repository.impl;

import ua.nure.kaverin.library.model.Book;
import ua.nure.kaverin.library.repository.BookRepository;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

public class StubBookRepository implements BookRepository {
    @Override
    public List<Book> getAllBooks() {
        return Arrays.asList(new Book(), new Book("1", "2", "3", new Date(1000000), 1));
    }
}
