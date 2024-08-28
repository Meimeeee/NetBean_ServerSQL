/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

/**
 *
 * @author ngoct
 */
public class Book {

    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private int year;
    private String genre;
    private int quantity;
    private float price;

    public Book(String bookId, String title, String author, String publisher, int year, String genre, int quantity, float price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.year = year;
        this.genre = genre;
        this.quantity = quantity;
        this.price = price;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {

        return "Book Details:\n"
                + "--------------------------------------------------\n"
                + "| Field       | Value                            |\n"
                + "--------------------------------------------------\n"
                + "| Book ID     : " + bookId + "\n"
                + "| Title       : " + title + "\n"
                + "| Author      : " + author + "\n"
                + "| Publisher   : " + publisher + "\n"
                + "| Year        : " + year + "\n"
                + "| Genre       : " + genre + "\n"
                + "| Quantity    : " + quantity + "\n"
                + "| Price       : " + price + "\n"
                + "--------------------------------------------------";
    }
}
