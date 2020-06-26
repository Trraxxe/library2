package ua.nure.kaverin.library.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import ua.nure.kaverin.library.repository.mongo.MongoRepositoryImpl;

public final class BookRepositoryFactory {

    private static BookRepositoryFactory instance;

    private JdbcTemplate jdbcTemplate;

    private BookRepositoryFactory(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public static synchronized BookRepositoryFactory getInstance(JdbcTemplate jdbcTemplate) {
        if (instance == null) {
            instance = new BookRepositoryFactory(jdbcTemplate);
        }
        return instance;
    }

    public BookRepository getBookRepository() {
        return new MongoRepositoryImpl();
    }
}
