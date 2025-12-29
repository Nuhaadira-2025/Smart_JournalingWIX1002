/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
        System.out.print("Email: ");
        String email = sc.nextLine();

        System.out.print("Password: ");
        String rawPassword = sc.nextLine();

        // ENCRYPT the password using PasswordSecurity class
        String encryptedPassword = PasswordSecurity.encrypt(rawPassword);

        System.out.print("Display Name: ");
        String displayName = sc.nextLine();

        // Create User object (THIS is combining)
        User user = new User(email, encryptedPassword, displayName);
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
    String rawPassword = sc.nextLine();

//encrypt the input to match the stored encrypted password
    String encryptedInput = PasswordSecurity.encrypt(rawPassword);

    boolean found = false;
    for (User u : users) {
        // 2. Compare the encrypted input with the stored encrypted password
        if (u.email.equals(email) && u.password.equals(encryptedInput)) {
            System.out.println("Welcome " + u.displayName);
            found = true;
            
            // 3. Instead of exiting, go to the journal menu
            journalpage jp = new journalpage();
            jp.displayJournalMenu();
            return; 
        }
    }
    
    // 4. Print this ONLY if the loop finishes without finding a match
    if (!found) {
        System.out.println("Login failed");
    }
}




}
    




    

    




