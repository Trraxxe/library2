package ua.nure.kaverin.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;
import ua.nure.kaverin.library.model.Book;
import ua.nure.kaverin.library.service.BookService;

import java.sql.Date;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping()
    public ModelAndView getAllBooks() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("books", bookService.getAllBooks());
        modelAndView.setViewName("books.html");
        return modelAndView;
    }

    @PostMapping()
    public View createBook(@RequestParam String bookName,
                           @RequestParam String editionName,
                           @RequestParam Date editionDate,
                           @RequestParam int booksCount) {
        Book book = new Book(null, bookName, editionName, editionDate, booksCount);
        bookService.createBook(book);
        return new RedirectView("/books");
    }

    @PostMapping("/{id}")
    public View updateBook(@RequestParam String bookName,
                           @RequestParam String editionName,
                           @RequestParam Date editionDate,
                           @RequestParam int booksCount,
                           @PathVariable String id) {
        Book book = new Book(id, bookName, editionName, editionDate, booksCount);
        bookService.updateBook(book);
        return new RedirectView("/books");
    }

    @GetMapping("/{id}/delete")
    public View deleteBook(@PathVariable String id) {
        bookService.deleteBook(id);
        return new RedirectView("/books");
    }

    @GetMapping("/create")
    public ModelAndView getCreateBookPage() {
        return new ModelAndView("createBook.html");
    }

    @GetMapping("/{id}")
    public ModelAndView getUpdatePage(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView("updateBook.html");
        modelAndView.addObject("book", bookService.getBookById(id));
        return modelAndView;
    }
}
