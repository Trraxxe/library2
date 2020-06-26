package ua.nure.kaverin.library.repository.mongo;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import ua.nure.kaverin.library.model.Book;
import ua.nure.kaverin.library.repository.BookRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

@Repository
public class MongoRepositoryImpl implements BookRepository {

    @Override
    public List<Book> getAllBooks() {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("books");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("books");
            FindIterable<Document> documents = mongoCollection.find();
            List<Book> books = new ArrayList<>();
            for (Document document : documents) {
                books.add(new Book(
                        document.getObjectId("_id").toString(),
                        document.getString("bookName"),
                        document.getString("editionName"),
                        new Date(document.getDate("editionDate").getTime()),
                        document.getInteger("booksCount")));
            }
            return books;
        }
    }

    @Override
    public void createBook(Book book) {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("books");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("books");
            Map<String, Object> bookMap = new HashMap<>();
            bookMap.put("bookName", book.getBookName());
            bookMap.put("editionName", book.getEditionName());
            bookMap.put("editionDate", book.getEditionDate());
            bookMap.put("booksCount", book.getBooksCount());
            mongoCollection.insertOne(new Document(bookMap));
        }
    }

    @Override
    public void updateBook(Book book) {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("books");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("books");
            mongoCollection.updateOne(Filters.eq("_id", new ObjectId(book.getId())),
                    combine(set("bookName", book.getBookName()),
                            set("editionName", book.getEditionName()),
                            set("editionDate", book.getEditionDate()),
                            set("bookCount", book.getBooksCount())));
        }
    }

    @Override
    public Book getBookById(String id) {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("books");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("books");
            FindIterable<Document> books = mongoCollection.find(new Document("_id", new ObjectId(id)));
            Document document = books.cursor().tryNext();
            Book book = null;
            if (document != null) {
                book = new Book(
                        document.getObjectId("_id").toString(),
                        document.getString("bookName"),
                        document.getString("editionName"),
                        new Date(document.getDate("editionDate").getTime()),
                        document.getInteger("booksCount"));
            }
            return book;
        }
    }

    @Override
    public void deleteBook(String id) {
        try (MongoClient mongoClient = MongoClients.create()) {
            MongoDatabase mongoDatabase = mongoClient.getDatabase("books");
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("books");
            mongoCollection.deleteOne(new Document("_id", new ObjectId(id)));
        }
    }
}
