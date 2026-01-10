package smartjournaling;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class journalPage {
    private Scanner scanner = new Scanner(System.in);
    private User currentUser;
    private journalStorage storage; 

    public journalPage(User user) {
        this.currentUser = user;
        this.storage = new journalStorage();
    }

    public void start() {
        while (true) {
            LocalDate today = LocalDate.now();
            List<JournalEntry> history = storage.getUserEntries(currentUser.getEmail());

            System.out.println("\n=== Journal Menu ===");
            
            // weekly summary
            System.out.println("1. View Weekly Summary"); 
            
            System.out.println("--- Journal Dates ---");
            
            
            int optionCounter = 2;
            
            // List past dates
            for (JournalEntry entry : history) {
                if (!entry.getDate().equals(today.toString())) {
                    System.out.println(optionCounter + ". " + entry.getDate());
                    optionCounter++;
                }
            }

            // Options for Today and Logout
            int todayOption = optionCounter;
            System.out.println(todayOption + ". " + today + " (Today)");
            
            int logoutOption = todayOption + 1;
            System.out.println(logoutOption + ". Logout"); 

            System.out.print("Select option: \n> ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);

                
                if (choice == 1) {
                    new WeeklySummary().show(currentUser);
                } 
                else if (choice == todayOption) {
                    handleToday(today);
                } 
                else if (choice == logoutOption) {
                    break;
                } 
                
                else if (choice > 1 && choice < todayOption) {
                    
                    JournalEntry selectedEntry = history.get(choice - 2);
                    viewEntry(selectedEntry);
                } else {
                    System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

   
    private void handleToday(LocalDate date) {
        JournalEntry existing = storage.getEntry(currentUser.getEmail(), date.toString());

        if (existing == null) {
            System.out.println("Enter your journal entry for " + date + ":");
            System.out.print("> ");
            String text = scanner.nextLine();
            
            System.out.println("Processing...");
            String mood = SentimentAnalysis.getMood(text);
            String rawJson = WeatherAPI.getWeather();
            String weather = extractWeather(rawJson);

            JournalEntry entry = new JournalEntry(currentUser.getEmail(), date.toString(), text, mood, weather);
            storage.saveEntry(entry);

            System.out.println("Saved! Mood: " + mood + " | Weather: " + weather);

        } else {
            System.out.println("\nEntry exists for today.");
            System.out.println("1. View Journal");
            System.out.println("2. Edit Journal");
            System.out.print("> ");
            String subChoice = scanner.nextLine();
            
            if (subChoice.equals("1")) {
                viewEntry(existing);
            } else if (subChoice.equals("2")) {
                System.out.println("Current Text: " + existing.getContent());
                System.out.println("Enter NEW text:");
                System.out.print("> ");
                String newText = scanner.nextLine();

                System.out.println("Re-analyzing mood...");
                String newMood = SentimentAnalysis.getMood(newText);
                String oldWeather = existing.getWeather();

                JournalEntry updated = new JournalEntry(currentUser.getEmail(), date.toString(), newText, newMood, oldWeather);
                storage.updateEntry(updated); 
                
                System.out.println("Entry Updated! New Mood: " + newMood);
            }
        }
    }

    private void viewEntry(JournalEntry entry) {
        System.out.println("\n=== Journal Entry for " + entry.getDate() + " ===");
        System.out.println("Content: " + entry.getContent());
        System.out.println("Mood:    " + entry.getMood());
        System.out.println("Weather: " + entry.getWeather());
        System.out.println("---------------------------------");
        System.out.println("Press Enter to go back.");
        scanner.nextLine();
    }

    private String extractWeather(String json) {
        String key = "\"summary_forecast\":\"";
        int start = json.indexOf(key);
        if (start == -1) return "Unknown";
        start += key.length();
        int end = json.indexOf("\"", start);
        return (end == -1) ? "Unknown" : json.substring(start, end);
    }
}
