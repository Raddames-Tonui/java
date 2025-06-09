package model;

import java.util.Date;

public class Book {
    private int bookId;
    private String bookName;
    private String bookAuthor;
    private int numberOfCopies;
    private Date datePublished;

    // Constructor
    public Book(int bookId, String bookName, String bookAuthor, int numberOfCopies, Date datePublished) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.numberOfCopies = numberOfCopies;
        this.datePublished = datePublished;
    }

    // Getters and Setters
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    // toString for display
    @Override
    public String toString() {
        return "[" + bookId + "] " + bookName + " by " + bookAuthor +
                ", Copies: " + numberOfCopies + ", Published: " + datePublished;
    }
}
