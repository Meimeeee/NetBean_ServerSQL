/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import JDBC.JDBC_connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class BookManager {

    public static BookManager instance;

    public static BookManager getInstance() {
        if (instance == null) {
            instance = new BookManager();
        }
        return instance;
    }

    //1.add
    public Book input() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Title: ");
        String title = sc.nextLine();
        System.out.print("Enter Author: ");
        String author = sc.nextLine();
        System.out.print("Enter Publisher: ");
        String publisher = sc.nextLine();
        System.out.print("Enter year of publisher: ");
        int year = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Genre: ");
        String genre = sc.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter price: ");
        float price = sc.nextFloat();
        return new Book(id, title, author, publisher, year, genre, quantity, price);
    }

    public void insert(Book b) throws SQLException {
        Connection connection = JDBC_connection.getConnection();
        PreparedStatement ps = connection.prepareStatement("INSERT INTO book (id, title, author, publisher, published_year, genre, quantity, price) VALUES( ?, ?, ?, ?, ?, ?, ?, ?)");
        ps.setString(1, b.getBookId());
        ps.setString(2, b.getTitle());
        ps.setString(3, b.getAuthor());
        ps.setString(4, b.getPublisher());
        ps.setInt(5, b.getYear());
        ps.setString(6, b.getGenre());
        ps.setInt(7, b.getQuantity());
        ps.setFloat(8, b.getPrice());

        //execute the insert
        int rowAffected = ps.executeUpdate();
        if (rowAffected > 0) {
            System.out.println("Insert successfully!!!");
        } else {
            System.out.println("Insert failed!!!!!!!!!!");
        }
        //close resources
//        ps.close();
//        connection.close();
    }

    public List<Book> listBook() throws SQLException {
        List<Book> books = new ArrayList<>();
        Connection connection = JDBC_connection.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM book;");
        ResultSet rs = ps.executeQuery();

        //add object to empty list: điền thông tin vào danh sách trống
        while (rs.next()) {//sử dụng ResultSet rồi dùng while để duyệt từng hàng
            Book b = new Book(
                    rs.getString("id"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("publisher"),
                    rs.getInt("published_year"),
                    rs.getString("genre"),
                    rs.getInt("quantity"),
                    rs.getFloat("price")
            );
            books.add(b);
        }

        //close resource
//        rs.close();
//        ps.close();
//        connection.close();
        return books;
    }

    //4
    public void showbook() throws SQLException {
        List<Book> books = listBook();
        if(!books.isEmpty()){
            for (Book book : books) {
            System.out.println(book);
        }
        } else {
            System.out.println("EMPTY!!!!!!");
        }
        
    }

    //2
    public void remove_id() throws SQLException {
        Connection connection = JDBC_connection.getConnection();
        System.out.print("Enter Book ID: ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM book WHERE id = ?;");
        ps.setString(1, id);
        int rowAffected = ps.executeUpdate();

        if (rowAffected > 0) {
            System.out.println("Book with id \\" + id + "\\ has been removed");
        } else {
            System.out.println("No book found with id \\" + id + "\\");
        }
    }

    public void remove_title() throws SQLException {
        Connection connection = JDBC_connection.getConnection();
        System.out.print("Enter Book title: ");
        Scanner sc = new Scanner(System.in);
        String title = sc.nextLine();
        PreparedStatement ps = connection.prepareStatement("DELETE FROM book WHERE title = ?;");
        ps.setString(1, title);

        int rowAffected = ps.executeUpdate();
        if (rowAffected > 0) {
            System.out.println("Book with title \\" + title + "\\ has been removed");
        } else {
            System.out.println("no book found with title \\" + title + "\\");
        }
    }

    //3
    public void updateBook() throws SQLException {
        Connection connection = JDBC_connection.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter id: ");
        String id = sc.nextLine();
        System.out.print("Enter quantity: ");
        int quantity = sc.nextInt();
        System.out.print("Enter price: ");
        float price = sc.nextFloat();

        PreparedStatement ps = connection.prepareStatement("UPDATE book SET quantity = ?, price = ? WHERE id = ?");
        ps.setInt(1, quantity);
        ps.setFloat(2, price);
        ps.setString(3, id);

        int rowAffected = ps.executeUpdate();
        if (rowAffected > 0) {
            System.out.println("Update book successfully!!!");
        } else {
            System.out.println("Update failed!!!!!!!!!!!!!!");
        }
    }

    //5. 
    public void searchByTitle() throws SQLException {
        Connection connection = JDBC_connection.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter title: ");
        String title = sc.nextLine();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM book WHERE title LIKE ?");
        ps.setString(1, "%" + title + "%");
        ResultSet rs = ps.executeQuery();
        boolean found = false;
        while (rs.next()) {
            found = true;
            System.out.println("Book Details:\n");
            System.out.println("--------------------------------------------------\n");
            System.out.println("| Field       | Value                            |\n");
            System.out.println("--------------------------------------------------\n");
            System.out.println("| Book ID     : " + rs.getString("id") + "\n");
            System.out.println("| Title       : " + rs.getString("title") + "\n");
            System.out.println("| Author      : " + rs.getString("author") + "\n");
            System.out.println("| Publisher   : " + rs.getString("publisher") + "\n");
            System.out.println("| Year        : " + rs.getInt("published_year") + "\n");
            System.out.println("| Genre       : " + rs.getString("id") + "\n");
            System.out.println("| Quantity    : " + rs.getInt("quantity") + "\n");
            System.out.println("| Price       : " + rs.getFloat("price") + "\n");
            System.out.println("--------------------------------------------------");
        }
        if (!found) {
            System.out.println("Not found!!");
        }
    }

    //6. author
    public void searchByAuthor() throws SQLException {
        Connection connection = JDBC_connection.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Author name: ");
        String author = sc.nextLine();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM book WHERE author LIKE ?");
        ps.setString(1, "%" + author + "%");
        ResultSet rs = ps.executeQuery();
        boolean found = false;

        while (rs.next()) {
            found = true;
            System.out.println("Book Details:\n");
            System.out.println("--------------------------------------------------\n");
            System.out.println("| Field       | Value                            |\n");
            System.out.println("--------------------------------------------------\n");
            System.out.println("| Book ID     : " + rs.getString("id") + "\n");
            System.out.println("| Title       : " + rs.getString("title") + "\n");
            System.out.println("| Author      : " + rs.getString("author") + "\n");
            System.out.println("| Publisher   : " + rs.getString("publisher") + "\n");
            System.out.println("| Year        : " + rs.getInt("published_year") + "\n");
            System.out.println("| Genre       : " + rs.getString("id") + "\n");
            System.out.println("| Quantity    : " + rs.getInt("quantity") + "\n");
            System.out.println("| Price       : " + rs.getFloat("price") + "\n");
            System.out.println("--------------------------------------------------");
        }
        if (!found) {
            System.out.println("Not found!!");
        }
    }

    //7. genre
    public void searchByGenre() throws SQLException {
        Connection connection = JDBC_connection.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter genre of book: ");
        String genre = sc.nextLine();

        PreparedStatement ps = connection.prepareStatement("SELECT * FROM book WHERE genre Like ?");
        ps.setString(1, "%" + genre + "%");
        ResultSet rs = ps.executeQuery();
        boolean found = false;

        while (rs.next()) {
            found = true;
            System.out.println("Book Details:\n");
            System.out.println("--------------------------------------------------\n");
            System.out.println("| Field       | Value                            |\n");
            System.out.println("--------------------------------------------------\n");
            System.out.println("| Book ID     : " + rs.getString("id") + "\n");
            System.out.println("| Title       : " + rs.getString("title") + "\n");
            System.out.println("| Author      : " + rs.getString("author") + "\n");
            System.out.println("| Publisher   : " + rs.getString("publisher") + "\n");
            System.out.println("| Year        : " + rs.getInt("published_year") + "\n");
            System.out.println("| Genre       : " + rs.getString("id") + "\n");
            System.out.println("| Quantity    : " + rs.getInt("quantity") + "\n");
            System.out.println("| Price       : " + rs.getFloat("price") + "\n");
            System.out.println("--------------------------------------------------");
        }
        if (!found) {
            System.out.println("Not found!!");
        }
    }
    //8.year
    public void searchByYear() throws SQLException{
        Connection connection = JDBC_connection.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter year of book published: "); int year = sc.nextInt();
        
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM book WHERE published_year  = ?");
        ps.setInt(1, year);
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
                System.out.println("Book Details:\n");
                System.out.println("--------------------------------------------------\n");
                System.out.println("| Field       | Value                            |\n");
                System.out.println("--------------------------------------------------\n");
                System.out.println("| Book ID     : " + rs.getString("id")+ "\n");
                System.out.println("| Title       : " + rs.getString("title") + "\n");
                System.out.println("| Author      : " + rs.getString("author") + "\n");
                System.out.println("| Publisher   : " + rs.getString("publisher") + "\n");
                System.out.println("| Year        : " + rs.getInt("published_year") + "\n");
                System.out.println("| Genre       : " + rs.getString("id") + "\n");
                System.out.println("| Quantity    : " + rs.getInt("quantity") + "\n");
                System.out.println("| Price       : " + rs.getFloat("price") + "\n");
                System.out.println("--------------------------------------------------");
        } else {
            System.out.println("Not found!!");
        }
    }
            
    //9.show book by ID
    public void showBookByID() throws SQLException{
        Connection connection = JDBC_connection.getConnection();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter book ID: "); String id = sc.nextLine();
        
        PreparedStatement ps = connection.prepareStatement("SELECT * FROM book WHERE id = ?");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        
        if(rs.next()){////////////////////////////////////////////////////////////////////// hoặc dùng while
                System.out.println("Book Details:\n");
                System.out.println("--------------------------------------------------\n");
                System.out.println("| Field       | Value                            |\n");
                System.out.println("--------------------------------------------------\n");
                System.out.println("| Book ID     : " + rs.getString("id")+ "\n");
                System.out.println("| Title       : " + rs.getString("title") + "\n");
                System.out.println("| Author      : " + rs.getString("author") + "\n");
                System.out.println("| Publisher   : " + rs.getString("publisher") + "\n");
                System.out.println("| Year        : " + rs.getInt("published_year") + "\n");
                System.out.println("| Genre       : " + rs.getString("id") + "\n");
                System.out.println("| Quantity    : " + rs.getInt("quantity") + "\n");
                System.out.println("| Price       : " + rs.getFloat("price") + "\n");
                System.out.println("--------------------------------------------------");
        } else {
            System.out.println("No book founnd with ID: " + id);
        }
    }
    //hiển thị số lượng quyển sách trong DB
    public void totalBookCount() throws SQLException{
        Connection connection = JDBC_connection.getConnection();
        int totalBooks = 0;
        PreparedStatement ps = connection.prepareStatement("SELECT COUNT(*) AS total FROM book");
        ResultSet rs = ps.executeQuery();
        if(rs.next()){
            System.out.println(rs.getInt("total"));
        }
        
    }
}
