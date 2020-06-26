package ua.nure.kaverin.library.repository.sql;

import org.springframework.jdbc.core.JdbcTemplate;
import ua.nure.kaverin.library.model.Book;
import ua.nure.kaverin.library.repository.BookRepository;

import java.util.List;

public class BookRepositoryImpl implements BookRepository {

    private static final String GET_ALL_BOOKS = "select b.id, b.name as book_name, e.name as edition_name, b.edition_date, " +
            "count(c.catalog_id) as books_count from books b " +
            "join editions e on e.id = b.edition_id " +
            "left join catalog c on c.book_id = b.id group by c.book_id";

    private JdbcTemplate template;

    public BookRepositoryImpl(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public List<Book> getAllBooks() {
        return template.query(con -> con.prepareStatement(GET_ALL_BOOKS), (rs, row) -> {
            Book book = new Book();
            book.setId(rs.getString("id"));
            book.setBookName(rs.getString("book_name"));
            book.setEditionName(rs.getString("edition_name"));
            book.setEditionDate(rs.getDate("edition_date"));
            book.setBooksCount(rs.getInt("books_count"));
            return book;
        });
    }

    @Override
    public void createBook(Book book) {

    }
}
