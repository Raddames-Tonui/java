package service;

import model.Book;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class BookService {
    private final List<Book> bookList = new ArrayList<>();

    // a. Add a new book to the list
    public void addBook(Book book) {
        bookList.add(book);
    }

    // b. Get book at a specific position
    public Book getBook(int index) {
        if (index >= 0 && index < bookList.size()) {
            return bookList.get(index);
        }
        return null;
    }

    // c. Update book details at a position
    public void updateBook(int index, Book updatedBook) {
        if (index >= 0 && index < bookList.size()) {
            bookList.set(index, updatedBook);
        }
    }

    // d.1 Delete a book by position
    public void deleteBookByIndex(int index) {
        if (index >= 0 && index < bookList.size()) {
            bookList.remove(index);
        }
    }

    // d.2 Delete books by a property (e.g., author or name)
    public void deleteBookByPredicate(Predicate<Book> predicate) {
        bookList.removeIf(predicate);
    }

    // e. Display all books
    public void displayBooks() {
        bookList.forEach(System.out::println);
    }

    // f. Display books ordered by a property
    public void displaySortedBooks(String property, boolean ascending) {
        Comparator<Book> comparator;

        switch (property.toLowerCase()) {
            case "bookid" -> comparator = Comparator.comparingInt(Book::getBookId);
            case "bookname" -> comparator = Comparator.comparing(Book::getBookName);
            case "bookauthor" -> comparator = Comparator.comparing(Book::getBookAuthor);
            case "numberofcopies" -> comparator = Comparator.comparingInt(Book::getNumberOfCopies);
            case "datepublished" -> comparator = Comparator.comparing(Book::getDatePublished);
            default -> {
                System.out.println("Invalid property for sorting.");
                return;
            }
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        bookList.stream().sorted(comparator).forEach(System.out::println);
    }

    // g. Filter books using streams by a property
    public void displayFilteredBooks(Predicate<Book> filter) {
        bookList.stream().filter(filter).forEach(System.out::println);
    }
}
