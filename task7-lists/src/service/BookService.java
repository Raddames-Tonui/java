package service;

import java.util.*;
import java.util.function.Predicate;

import model.Book;

public class BookService {
    private final List<Book> bookList = new ArrayList<>();

    // a. Add a new book to the list
    public void addBook(Book book){
        bookList.add(book);
    }

    // b. Get book at a specific position
    public Book getBook(int index){
        if (index >= 0 && index < bookList.size()){
            return bookList.get(index);
        }
        return null;
    }

    // c. Update book details at a position
    public void updateBook(int index, Book updatedBook){
        if (index >= 0 && index < bookList.size()){
            bookList.set(index, updatedBook);            
        }
        else {
            System.out.println("Index out of bounds.");
        }
    }

    // d.1 Delete a book by position
    public void deleteBookByIndex(int index){
        if( index >= 0 && index < bookList.size()){
            bookList.remove(index);
        }
        else {
            System.out.println("Index out of bounds.");
        }
    }

    // d.2 Delete books by a property (e.g., author or name)
    public void deleteBookByPredicate(Predicate<Book> predicate){
        bookList.removeIf(predicate);
    }

}
