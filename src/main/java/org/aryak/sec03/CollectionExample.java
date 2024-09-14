package org.aryak.sec03;

import org.aryak.model.sec03.Book;
import org.aryak.model.sec03.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CollectionExample {

    private static final Logger log = LoggerFactory.getLogger(CollectionExample.class);

    public static void main(String[] args) {

        var booksList = getBooksList();
        var library = Library.newBuilder()
                .addAllBooks(booksList)
                .setName("Central library")
                .build();

        log.info("Library : {}", library);

    }

    private static List<Book> getBooksList() {
        var book1 = Book.newBuilder()
                .setAuthor("J K Rowling")
                .setName("Harry Potter 1")
                .setPublishedYear(1997)
                .build();

        var book2 = book1.toBuilder()
                .setPublishedYear(1998)
                .setName("Harry Potter 2")
                .build();

        var book3 = book1.toBuilder()
                .setPublishedYear(1999)
                .setName("Harry Potter 3")
                .build();

        return List.of(book1, book2, book3);
    }
}
