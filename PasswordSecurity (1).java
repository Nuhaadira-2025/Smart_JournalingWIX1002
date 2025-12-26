package passwordsecurity;


public class PasswordSecurity {

    
    private static final int SHIFT = 3;

    
    public static String encrypt(String password) {
        if (password == null) return null;
        
        StringBuilder encrypted = new StringBuilder();
        for (char character : password.toCharArray()) {
            // Shift the character by 3
            encrypted.append((char) (character + SHIFT));
        }
        return encrypted.toString();
    }

    
    public static String decrypt(String encryptedPassword) {
        if (encryptedPassword == null) return null;

        StringBuilder decrypted = new StringBuilder();
        for (char character : encryptedPassword.toCharArray()) {
            // Shift the character back by 3
            decrypted.append((char) (character - SHIFT));
        }
        return decrypted.toString();
    }
    
}