/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author NUHANA
 */
public class User {
   
   String email;
   String password;
   String displayName;
  
   public User (String email, String password, String displayName) {
        this.email = email;
        this.password = password;
        this.displayName = displayName;
   }
   public String getEmail() {
        return email;
    }
   public String getpassword() {
        return password;
    }
   public String getdisplayName() {
        return displayName;
    }
}
   



