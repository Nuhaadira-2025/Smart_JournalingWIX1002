package smartjournaling;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class WeeklySummary {
    
    public void show(User user) {
        journalStorage storage = new journalStorage();
        List<JournalEntry> entries = storage.getUserEntries(user.getEmail());
        
        System.out.println("\n=== Weekly Mood & Weather Summary (Past 7 Days) ===");
        
        if (entries.isEmpty()) {
            System.out.println("No journal entries found.");
            return;
        }

        
        Collections.sort(entries, new Comparator<JournalEntry>() {
            @Override
            public int compare(JournalEntry e1, JournalEntry e2) {
                return LocalDate.parse(e2.getDate()).compareTo(LocalDate.parse(e1.getDate()));
            }
        });

        
        int positiveCount = 0;
        int negativeCount = 0;
        int neutralCount = 0;
        int rainyCount = 0;
        int sunnyCount = 0;

        int daysToShow = Math.min(entries.size(), 7); // Show max 7 days

        System.out.println("----------------------------------------------------------------");
        System.out.printf("%-12s | %-10s | %-35s\n", "Date", "Mood", "Weather");
        System.out.println("----------------------------------------------------------------");
        
        for (int i = 0; i < daysToShow; i++) {
            JournalEntry entry = entries.get(i);
            
            
            String weather = entry.getWeather();
            if (weather.length() > 30) weather = weather.substring(0, 32) + "...";
            System.out.printf("%-12s | %-10s | %-35s\n", entry.getDate(), entry.getMood(), weather);

            
            if (entry.getMood().contains("POSITIVE")) positiveCount++;
            else if (entry.getMood().contains("NEGATIVE")) negativeCount++;
            else neutralCount++;

            
            String w = weather.toLowerCase();
            if (w.contains("hujan") || w.contains("rain") || w.contains("ribut")) rainyCount++;
            else sunnyCount++; 
        }
        System.out.println("----------------------------------------------------------------");

        
        System.out.println("\n--- Weekly Overview ---");
        System.out.println("Moods:   " + positiveCount + " Positive, " + negativeCount + " Negative, " + neutralCount + " Neutral");
        System.out.println("Weather: " + rainyCount + " Rainy/Stormy days, " + sunnyCount + " Other days");
        System.out.println("-----------------------");
    }
}
