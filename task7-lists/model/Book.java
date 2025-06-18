package model;

import java.sql.Date;

public class Book {
    private int bookID;
    private String bookName;
    private String bookAuthor;
    private int numberOfCopies;
    private Date datePublished;

    // Creating a constructor
    public Book (int bookID, String bookName, String bookAuthor, int numberOfCopies, Date datePublished){
        this.bookID = bookID;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.numberOfCopies = numberOfCopies;
        this.datePublished = datePublished;
    }
    
    // Getters and Setters
    public int getBookID(){
        return bookID;
    }

    public void setBookID(int bookID){
        this.bookID = bookID;
    }

    public String getBookName(){
        return bookName;
    }

    public void setBookName(String bookName){
        this.bookName = bookName;
    }

    public String getBookAuthor(){
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor){
        this.bookAuthor = bookAuthor;
    }
    public int getNumberOfCopies(){
        return numberOfCopies;
    }
    public void setNumberOfCopies(int numberOfCopies){
        this.numberOfCopies = numberOfCopies;
    }
    public Date getDatePublished(){
        return datePublished;
    }
    public void setDatePublished(Date datePublished){
        this.datePublished = datePublished;
    }


    // Book does override a method from Object(java.lang.Object) (toString()), 
    // Every class in Java extends Object from java.lang.Object by default.
    @Override
    public String toString(){
        return "Book{" +
                "bookID=" + bookID +
                ", bookName='" + bookName + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", datePublished=" + datePublished +
                '}';
    }
}
