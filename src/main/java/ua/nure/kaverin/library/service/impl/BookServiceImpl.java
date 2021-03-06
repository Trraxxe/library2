package ua.nure.kaverin.library.service.impl;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import ua.nure.kaverin.library.model.Book;
import ua.nure.kaverin.library.repository.BookRepository;
import ua.nure.kaverin.library.repository.BookRepositoryFactory;
import ua.nure.kaverin.library.service.BookService;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository repository;

    public BookServiceImpl(JdbcTemplate jdbcTemplate) {
        this.repository = BookRepositoryFactory.getInstance(jdbcTemplate).getBookRepository();
    }

    @Override
    public List<Book> getAllBooks() {
        return repository.getAllBooks();
    }

    @Override
    public void createBook(Book book) {
        repository.createBook(book);
    }

    @Override
    public void updateBook(Book book) {
        repository.updateBook(book);
    }

    @Override
    public Book getBookById(String id) {
        return repository.getBookById(id);
    }

    @Override
    public void deleteBook(String id) {
        repository.deleteBook(id);
    }
}
