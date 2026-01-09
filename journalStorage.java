package smartjournaling;

import java.io.*;
import java.util.*;

public class journalStorage { 
    private static final String FILE_NAME = "JournalData.txt";

    
    public void saveEntry(JournalEntry entry) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            bw.write(formatEntry(entry));
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving journal: " + e.getMessage());
        }
    }

    
    public void updateEntry(JournalEntry updatedEntry) {
        List<JournalEntry> allEntries = loadAll();
        boolean found = false;

        for (int i = 0; i < allEntries.size(); i++) {
            JournalEntry current = allEntries.get(i);
            
            
            if (current.getEmail().trim().equals(updatedEntry.getEmail().trim()) && 
                current.getDate().trim().equals(updatedEntry.getDate().trim())) {
                
                allEntries.set(i, updatedEntry); 
                found = true;
                break;
            }
        }

        if (found) {
            rewriteFile(allEntries);
        } else {
            System.out.println("Debug: Could not find entry to update.");
        }
    }

    
    private void rewriteFile(List<JournalEntry> entries) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (JournalEntry e : entries) {
                bw.write(formatEntry(e));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error updating file: " + e.getMessage());
        }
    }

    
    private String formatEntry(JournalEntry e) {
        return e.getEmail() + "|" + e.getDate() + "|" + e.getContent() + "|" + e.getMood() + "|" + e.getWeather();
    }

    
    public JournalEntry getEntry(String email, String date) {
        for (JournalEntry entry : loadAll()) {
            if (entry.getEmail().trim().equals(email.trim()) && entry.getDate().trim().equals(date.trim())) {
                return entry;
            }
        }
        return null;
    }

    public List<JournalEntry> getUserEntries(String email) {
        List<JournalEntry> userEntries = new ArrayList<>();
        for (JournalEntry entry : loadAll()) {
            if (entry.getEmail().trim().equals(email.trim())) {
                userEntries.add(entry);
            }
        }
        return userEntries;
    }

    private List<JournalEntry> loadAll() {
        List<JournalEntry> list = new ArrayList<>();
        File file = new File(FILE_NAME);
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 5) {
                    list.add(new JournalEntry(parts[0], parts[1], parts[2], parts[3], parts[4]));
                }
            }
        } catch (IOException e) { }
        return list;
    }
}
