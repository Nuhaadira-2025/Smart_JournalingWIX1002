package group.assingment;

import java.io.*;
import java.util.*;

public class UserManager {

    private List<User> users = new ArrayList<>();

    public UserManager() {
        loadUsers();
    }

    private void loadUsers() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("UserData.txt"));
            String email;

            while ((email = br.readLine()) != null) {
                String displayName = br.readLine();
                String password = br.readLine(); // already encrypted
                users.add(new User(email, displayName, password));
            }
            br.close();

        } catch (IOException e) {
            System.out.println("UserData.txt not found.");
        }
    }

    
    public User login(String email, String password) {
        String encryptedInput = encryptPassword(password);

        for (User u : users) {
            if (u.getEmail().equals(email)
                    && u.getPassword().equals(encryptedInput)) {
                return u;
            }
        }
        return null;
    }

    public boolean emailExists(String email) {
        for (User u : users) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    
    public void register(String email, String displayName, String password) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("UserData.txt", true));

            String encryptedPassword = encryptPassword(password);

            bw.newLine();
            bw.write(email);
            bw.newLine();
            bw.write(displayName);
            bw.newLine();
            bw.write(encryptedPassword);
            bw.close();

            users.add(new User(email, displayName, encryptedPassword));
            System.out.println("Registration successful!");

        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }

    
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
}
