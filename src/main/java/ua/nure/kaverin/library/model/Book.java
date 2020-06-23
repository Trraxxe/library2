package ua.nure.kaverin.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private String id;
    private String bookName;
    private String editionName;
    private Date editionDate;
    private int booksCount;
}
