/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;
import java.util.ArrayList;
import java.util.Scanner;


/**
 *
 * @author NUHANA
 */
public class Login {
    static ArrayList<User> users = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        loadUsersFromFile(); // LOAD users first


    while (true) {
        System.out.println("\n1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose option: ");

        int choice = sc.nextInt();
        sc.nextLine(); // IMPORTANT: clear buffer

        if (choice == 1) {
            register();
        } 
        else if (choice == 2) {
            login();
        } 
        else if (choice == 3) {
            System.out.println("Bye!");
            break;
        } 
        else {
            System.out.println("Invalid choice");
        }
    }
}

    static void register() {
        // ❌ If users already exist, block register
    if (!users.isEmpty()) {
        System.out.println("Users already exist. Please login.");
        return;
    }
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        System.out.print("Display Name: ");
        String displayName = sc.nextLine();

        // Create User object (THIS is combining)
        User user = new User(email, password, displayName);
        users.add(user);

        System.out.println("Registered successfully!");
    }
static void login() {
    if (users.isEmpty()) { 
    System.out.println("Register first"); 
    return;
    }
    
    System.out.print("Email: ");
    String email = sc.nextLine();

    System.out.print("Password: ");
    String password = sc.nextLine();

    for (User u : users) {
        if (u.email.equals(email) && u.password.equals(password)) {
            System.out.println("Welcome " + u.displayName);
            System.exit(0);
        }
    
       
              
       
      }
    
}static void loadUsersFromFile() {
    users.clear();

    try (Scanner fileScanner = new Scanner(new java.io.File("UserData.txt"))) {
        while (fileScanner.hasNextLine()) {

            String email = fileScanner.nextLine();
            if (email.isEmpty()) continue;

            String displayName = fileScanner.nextLine();
            String password = fileScanner.nextLine();

            User u = new User(email, password, displayName);
            users.add(u);
        }
    } catch (Exception e) {
        System.out.println("UserData.txt not found");
    }
}

}


    




    





