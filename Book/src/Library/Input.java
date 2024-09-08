/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Library;

import java.util.Scanner;

/**
 *
 * @author ngoct
 */
public class Input {
    public final static Scanner sc = new Scanner(System.in);
    
    public static int readInt(String question){
        while(true){
            try {
                System.out.println(question);
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("Wrong input format !!!");
                sc.nextLine();
            }
        }
    }
    
    public static float readFloat(String question){
        while(true){
            try {
                System.out.println(question);
                return sc.nextFloat();
            } catch (Exception e) {
                System.out.println("Wrong input format !!!");
                sc.nextLine();
            }
        }
    }
    
    
    
}
