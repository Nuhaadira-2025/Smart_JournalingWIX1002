package group.assingment;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        UserManager manager = new UserManager();
        User loggedInUser = null;

        while (loggedInUser == null) {

            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            if (choice == 1) {
                // LOGIN FLOW
                System.out.print("Email: ");
                String email = sc.nextLine();

                if (!manager.emailExists(email)) {
                    System.out.println("Account not found. Please register first.\n");
                    continue;
                }

                System.out.print("Password: ");
                String password = sc.nextLine();

                loggedInUser = manager.login(email, password);
                if (loggedInUser != null) {
                    System.out.println("\nLogin successful!");
                    System.out.println("Welcome, " + loggedInUser.getDisplayName());
                } else {
                    System.out.println("Wrong password.\n");
                }

            } else if (choice == 2) {
                // REGISTER FLOW
                System.out.print("Email: ");
                String email = sc.nextLine();

                if (manager.emailExists(email)) {
                    System.out.println("Email already exists. Redirecting to login...");

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    loggedInUser = manager.login(email, password);
                    if (loggedInUser != null) {
                        System.out.println("\nLogin successful!");
                        System.out.println("Welcome, " + loggedInUser.getDisplayName());
                    } else {
                        System.out.println("Wrong password.\n");
                    }

                } else {
                    System.out.print("Display Name: ");
                    String name = sc.nextLine();

                    System.out.print("Password: ");
                    String password = sc.nextLine();

                    manager.register(email, name, password);
                    System.out.println("You may now login.\n");
                }

            } else {
                System.out.println("Invalid choice.\n");
            }
        }

        sc.close();
    }
}

