package smartjournaling;

import java.io.*;
import java.util.*;

public class UserManager {

    private List<User> users = new ArrayList<>();

    public UserManager() {
        loadUsers();
    }

    private void loadUsers() {
        users.clear();
        File file = new File("UserData.txt");
        
        if (!file.exists()) {
            System.out.println("UserData.txt not found.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String email;
            while ((email = br.readLine()) != null) {
                if (email.trim().isEmpty()) continue; 
                
                
                String lineA = br.readLine();
                String lineB = br.readLine();
                
                if (lineA == null || lineB == null) break;

                String displayName;
                String password;

                
                
                if (lineA.startsWith("pw-") || lineA.startsWith("sz-")) {
                    password = lineA;
                    displayName = lineB;
                } else {
                    displayName = lineA;
                    password = lineB;
                }

                
                if (password.startsWith("pw-")) {
                    password = encryptPassword(password);
                }

                users.add(new User(email, displayName, password));
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    public User login(String email, String rawPassword) {
        // Encrypt input to match the stored (encrypted) password
        String encryptedInput = encryptPassword(rawPassword);

        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(encryptedInput)) {
                return u;
            }
        }
        return null;
    }

    public void register(String email, String displayName, String rawPassword) {
        String encryptedPassword = encryptPassword(rawPassword);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("UserData.txt", true))) {
            if (new File("UserData.txt").length() > 0) bw.newLine();
            
            bw.write(email);
            bw.newLine();
            bw.write(displayName);
            bw.newLine();
            bw.write(encryptedPassword);
            
            users.add(new User(email, displayName, encryptedPassword));
            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
        
    }

    //Caesar Cipher 
    private String encryptPassword(String password) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : password.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + 3) % 26 + base);
            }
            encrypted.append(c);
        }
        return encrypted.toString();
    }
    
    public boolean emailExists(String email) {
        for (User u : users) {
            if (u.getEmail().equals(email)) return true;
        }
        return false;
    }
    
}

