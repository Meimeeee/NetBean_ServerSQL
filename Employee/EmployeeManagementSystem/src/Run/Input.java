/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Run;

import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class Input {
    private static Scanner sc = new Scanner(System.in);
    
    public static int readInt(String question){ //đọc lỗi nhập không theo format
        while(true){
            try {
                System.out.println(question);
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong input format. It must be (int)");
            } finally {
                sc.nextLine();
            }
        }
    }
    
    public static String readString(String question){
        while(true){
            try {
                System.out.println(question);
                String input = sc.nextLine();
                if(input.isEmpty()){
                    System.out.println("Try again !!");
                    continue;
                }
                return input;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
    
    public static double readDouble(String question){
        while(true){
            try {
                System.out.println(question);
                return sc.nextDouble();
            } catch (Exception e) {
                System.out.println("Wrong input format. It must be (double) !!");
            } finally {
                sc.nextLine();
            }
        }
    }
    
    
}
