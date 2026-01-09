package smartjournaling;

import java.time.LocalTime; 
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserManager manager = new UserManager();
        User loggedInUser = null;

        while (loggedInUser == null) {
            System.out.println("\n=== WELCOME ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            
            if (!sc.hasNextInt()) {
                sc.next(); 
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 1) {
                System.out.print("Email: ");
                String email = sc.nextLine();
                System.out.print("Password: ");
                String password = sc.nextLine();

                loggedInUser = manager.login(email, password);
                
                if (loggedInUser != null) {
                    // Time-Based Greeting 
                    String greeting;
                    LocalTime now = LocalTime.now();
                    int hour = now.getHour();

                    if (hour >= 0 && hour < 12) {
                        greeting = "Good Morning";
                    } else if (hour >= 12 && hour < 17) {
                        greeting = "Good Afternoon";
                    } else {
                        greeting = "Good Evening";
                    }


                    System.out.println("\n" + greeting + ", " + loggedInUser.getDisplayName());
                    
                    
                    new journalPage(loggedInUser).start(); 
                    break; 
                } else {
                    System.out.println("Invalid credentials.");
                }

            } else if (choice == 2) {
                System.out.print("Email: ");
                String email = sc.nextLine();
                if (manager.emailExists(email)) {
                    System.out.println("Email exists.");
                } else {
                    System.out.print("Display Name: ");
                    String name = sc.nextLine();
                    System.out.print("Password: ");
                    String pass = sc.nextLine();
                    manager.register(email, name, pass);
                    System.out.println("Registered. Please login.");
                }
            } else if (choice == 3) {
                System.exit(0);
            }
        }
        sc.close();
    }
}
