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
                String password = br.readLine();
                users.add(new User(email, displayName, password));
            }
            br.close();
        } catch (IOException e) {
            System.out.println("UserData.txt not found.");
        }
    }

    public User login(String email, String password) {
        for (User u : users) {
            if (u.getEmail().equals(email) && u.getPassword().equals(password)) {
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
            bw.newLine();
            bw.write(email);
            bw.newLine();
            bw.write(displayName);
            bw.newLine();
            bw.write(password);
            bw.close();

            users.add(new User(email, displayName, password));
            System.out.println("Registration successful!");
        } catch (IOException e) {
            System.out.println("Error saving user.");
        }
    }
}

