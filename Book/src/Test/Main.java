/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import Library.Book;
import Library.BookManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class Main {

    public static void main(String[] args) throws SQLException {
        JDBC.JDBC_connection.init();
        Scanner sc = new Scanner(System.in);
        BookManager bookManager = BookManager.getInstance();

        try {
            while (true) {
                System.out.println();
                System.out.println("+-------------------------------+");
                System.out.println("|     Book Management Menu      |");
                System.out.println("+-------------------------------+");
                System.out.println("| 1. Add Book                   |");
                System.out.println("| 2. Remove Book by Title       |");
                System.out.println("| 3. Update Book Information    |");
                System.out.println("| 4. List All Books             |");
                System.out.println("| 5. Search                     |");
                System.out.println("| 6. View Book by ID            |");
                System.out.println("| 0. Exit                       |");
                System.out.println("+-------------------------------+");
                System.out.print("Enter your choice: ");
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1://add
                        Book b = bookManager.input();
                        bookManager.insert(b);
                        break;
                    case 2://remove
                        while (true) {
                            System.out.println("+---------------------------+");
                            System.out.println("|        Remove Book        |");
                            System.out.println("+---------------------------+");
                            System.out.println("| 1. Remove Book By Id      |");
                            System.out.println("| 2. Remove Book by Title   |");
                            System.out.println("| 0. Exit                   |");
                            System.out.println("+---------------------------+");
                            System.out.print("Enter your choice: ");

                            try {
                                int remove = sc.nextInt();
                                sc.nextLine();
                                if (remove == 1) {
                                    bookManager.remove_id();
                                } else if (remove == 2) {
                                    bookManager.remove_title();
                                } else if (remove == 0) {
                                    System.out.println("Baibaii");
                                    break;
                                } else {
                                    System.out.println("Invalid remove. Enter 1.. and 0 for exit");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid remove. Please try again");
                                sc.nextLine();
                            }
                        }
                        break;

                    case 3://update 
                        bookManager.updateBook();
                        break;

                    case 4://show
                        bookManager.showbook();
                        break;

                    case 5://research by title
                        boolean cho = true;
                        while (cho) {
                            System.out.println("+---------------------------+");
                            System.out.println("|        Search Book        |");
                            System.out.println("+---------------------------+");
                            System.out.println("| 1. Search Books by Author |");
                            System.out.println("| 2. Search Books by Genre  |");
                            System.out.println("| 3. Search Books by Year   |");
                            System.out.println("| 0. Exit                   |");
                            System.out.println("+---------------------------+");
                            System.out.print("Enter your choice: ");

                            try {
                                int search = sc.nextInt();
                                sc.nextLine();
                                switch (search) {
                                    case 1:
                                        bookManager.searchByAuthor();
                                        break;
                                    case 2:
                                        bookManager.searchByGenre();
                                        break;
                                    case 3:
                                        bookManager.searchByYear();
                                        break;
                                    case 0:
                                        System.out.println("Baibaii");
                                        cho = false;
                                        break;
                                    default:
                                        System.out.println("Input 1..3 and 0 to exit");
                                }
                            } catch (InputMismatchException e) {
                                System.out.println("Invalid search. Please try again");
                                sc.nextLine();
                            }
                        }
                        break;

                    case 6://show book by ID
                        bookManager.showBookByID();
                        break;
                    case 0://Exit
                        System.out.println("Baibaiii");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Try again!!!");
            sc.nextLine();
        }
    }
}
