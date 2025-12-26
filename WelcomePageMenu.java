import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Scanner;

public class WelcomePageMenu {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask for user name
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        // Display greeting based on GMT+8
        LocalTime time = LocalTime.now(ZoneId.of("GMT+8"));
        String greeting;
        if (time.isBefore(LocalTime.NOON)) {
            greeting = "Good Morning";
        } else if (time.isBefore(LocalTime.of(17, 0))) {
            greeting = "Good Afternoon";
        } else {
            greeting = "Good Evening";
        }
        System.out.println(greeting + ", " + name + "!");

        String choice;
        do {
            // Display menu
            System.out.println("\n--- Journal Menu ---");
            System.out.println("1. Create, Edit & View Journals");
            System.out.println("2. View Weekly Mood Summary");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextLine();

            switch (choice) {
                case "1":
                    System.out.println("You chose: Create, Edit & View Journals");
                    // Here you can later add actual journal functionality
                    break;
                case "2":
                    System.out.println("You chose: View Weekly Mood Summary");
                    // Here you can later add actual summary functionality
                    break;
                case "3":
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (!choice.equals("3"));

        sc.close();
    }
}