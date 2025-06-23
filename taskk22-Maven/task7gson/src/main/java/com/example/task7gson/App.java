package com.example.task7gson;

import com.example.task7gson.model.Book;
import com.example.task7gson.service.BookService;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

import java.text.SimpleDateFormat;


public class App {
    public static void main(String[] args) throws Exception {
        BookService service = new BookService();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        // Adding books
        service.addBook(new Book(1, "Effective Java", "Joshua Bloch", 5,
            new java.sql.Date(sdf.parse("2018-01-10").getTime())));
        service.addBook(new Book(2, "Clean Code", "Robert Martin", 3,
            new java.sql.Date(sdf.parse("2008-08-01").getTime())));
        service.addBook(new Book(3, "Java Concurrency", "Brian Goetz", 2,
            new java.sql.Date(sdf.parse("2006-05-19").getTime())));

        System.out.println("\nAll Books:");
        service.displayBooks();

        // System.out.println("\nGet Book at index 1:");
        // System.out.println(service.getBook(1));

        // Updating a book
        System.out.println("\nUpdating Book at index 2...");
        service.updateBook(2, new Book(3, "Java Concurrency in Practice", "Brian Goetz", 4,
            new java.sql.Date(sdf.parse("2006-05-19").getTime())));

        // System.out.println("\nBooks after update:");
        service.displayBooks();

        // Deleting by index
        // System.out.println("\nDeleting book at index 0...");
        service.deleteBookByIndex(0);

        // Deleting by predicate (book author)
        // System.out.println("\nDeleting books by author 'Robert Martin'...");
        service.deleteBookByPredicate(b -> b.getBookAuthor().equalsIgnoreCase("Robert Martin"));

        // System.out.println("\nBooks after deletions:");
        // service.displayBooks();

        // Adding more books for sorting and filtering
        service.addBook(new Book(4, "Spring in Action", "Craig Walls", 6,
            new java.sql.Date(sdf.parse("2021-02-15").getTime())));
        service.addBook(new Book(5, "Head First Java", "Kathy Sierra", 8,
            new java.sql.Date(sdf.parse("2005-06-10").getTime())));

        // System.out.println("\nBooks sorted by bookName (asc):");
        // service.displaySortedBooks("bookName", true);

        // System.out.println("\nBooks sorted by numberOfCopies (desc):");
        // service.displaySortedBooks("numberOfCopies", false);

        // System.out.println("\nBooks filtered by numberOfCopies > 5:");
        // service.displayFilteredBooks(b -> b.getNumberOfCopies() > 5);

        // System.out.println("\nBooks filtered by name containing 'Java':");
        // service.displayFilteredBooks(b -> b.getBookName().toLowerCase().contains("java"));
        
        
          //  Gson: Serialize and Deserialize Book List
        Gson gson = new Gson();
        List<Book> books = service.getAllBooks(); 
        String json = gson.toJson(books);
        System.out.println("\n📦 JSON Output:");
        System.out.println(json);

        Type bookListType = new TypeToken<List<Book>>() {}.getType();
        List<Book> deserializedBooks = gson.fromJson(json, bookListType);
        System.out.println("\n📥 Deserialized Books:");
        deserializedBooks.forEach(System.out::println);
    }
}
