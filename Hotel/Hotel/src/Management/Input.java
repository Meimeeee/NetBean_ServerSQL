/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class Input {

    private final static Scanner sc = new Scanner(System.in);

    public static int readInt(String question) {
        while (true) {
            try {
                System.out.println(question);
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong input format. It must be (int) !!!");
            } finally{
                sc.nextLine();
            }
        }
    }
    
    public static int readInt() {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong input format. It must be (int) !!!");
            } finally {
                sc.nextLine();
            }
        }
    }

    public static long readLong(String question) {
        while (true) {
            try {
                System.out.println(question);
                return sc.nextLong();
            } catch (Exception e) {
                System.out.println("Wrong input format. It must be (long) type !!!");
            } finally{
                sc.nextLine();
            }
        }
    }

    public static double readDouble(String question) {
        while (true) {
            try {
                System.out.println(question);
                return sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Wrong input format. It must be (double) type !!!");
            } finally {
                sc.nextLine();
            }
        }
    }

    public static String inputPhonNumber(String question) {
        while (true) {
            System.out.println(question);
            String phone = sc.nextLine();
            String phoneRegex = "^[0-9\\s]{10,15}$";
            if (phone.matches(phoneRegex)) {
                return phone;
            } else {
                System.out.println("Wrong phone format !!");
                System.out.println("Try again");
            }
        }
    }

    public static String readEmail(String question) {
        while (true) {
            System.out.println(question);
            String input = sc.nextLine();
            String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
            if (input.matches(emailRegex)) {
                return input;

            } else {
                System.out.println("Wrong email format !!");
                System.out.println("Try again.");
            }
        }
    }

    public static String readMailCheckDomain(String question, List<String> domains) {
        while (true) {
            String mail = readEmail(question);
            String domain = mail.split("@")[1];
            int index = domains.indexOf(domain);
            if (index >= 0) {
                return mail;
            } else {
                System.out.println("Email domain is not allowed !!");
                System.out.println("Try again !!");
            }
        }
    }

    public static String readString(String question) {
        while (true) {
            try {
                System.out.println(question);
                String input = sc.nextLine();
                if(input.isEmpty()){
                    System.out.println("Input again !!");
                    continue;
                }
            } catch (Exception e) {
                System.out.println("Wrong input format. It must be (Sring) !!!");
            }

        }
    }

    public static LocalDate readDate(String question) {
        DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter dateFormat2 = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        while (true) {
            System.out.println(question);
            String dateInput = sc.nextLine();

            try {
                return LocalDate.parse(dateInput, dateFormat1);
            } catch (DateTimeParseException e1) {
                try {
                    return LocalDate.parse(dateInput, dateFormat2);
                } catch (DateTimeParseException e2) {
                    System.out.println("Invalid date format. Please enter the date in the format dd-MM-yy or MM-dd-yy.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred while reading the date. Please try again.");
            }
        }
    }

    
    
    
    
    
    
}



// int, long, double
