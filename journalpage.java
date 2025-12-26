import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class journalpage {

    private Scanner scanner = new Scanner(System.in);
    
    
    private List<String> journalDates = new ArrayList<>(); 
    private String todayEntry = ""; 

    public void displayJournalMenu() {
        LocalDate today = LocalDate.now();
        
        while (true) {
            System.out.println("\n=== Journal Dates ===");
            
            System.out.println("1. 2025-10-08");
            System.out.println("2. 2025-10-09");
            System.out.println("3. 2025-10-10");
            System.out.println("4. " + today + " (Today)");
            
            
            if (todayEntry.isEmpty()) {
                System.out.print("Select a date to view journal, or create a new journal for today: \n> ");
            } else {
                System.out.print("Select a date to view journal, or edit the journal for today: \n> ");
            }

            String choice = scanner.nextLine();

            if (choice.equals("4")) {
                handleToday(today);
            } else if (choice.equals("1") || choice.equals("2") || choice.equals("3")) {
                viewPastEntry(choice);
            } else {
                break; 
            }
        }
    }

    private void handleToday(LocalDate date) {
        if (todayEntry.isEmpty()) {
            
            System.out.println("Enter your journal entry for " + date + ":");
            System.out.print("> ");
            todayEntry = scanner.nextLine();
            
            
            System.out.println("Journal saved successfully!");
        } 
        
        
        System.out.println("\nWould you like to:");
        System.out.println("1. View Journal");
        System.out.println("2. Edit Journal");
        System.out.println("3. Back to Dates");
        System.out.print("> ");
        
        String action = scanner.nextLine();
        switch (action) {
            case "1":
                System.out.println("=== Journal Entry for " + date + " ===");
                System.out.println(todayEntry);
                System.out.println("Press Enter to go back.");
                scanner.nextLine();
                break;
            case "2":
                System.out.println("Edit your journal entry for " + date + ":");
                System.out.print("> ");
                todayEntry = scanner.nextLine(); 
                break;
            default:
                break;
        }
    }

    private void viewPastEntry(String choice) {
        
        System.out.println("=== Journal Entry for Past Date ===");
        System.out.println("I had a great day at the park with my friends.");
        System.out.println("Press Enter to go back.");
        scanner.nextLine();
    }
}
    
