import java.sql.*;

public class SmartJournalDB {

    // Database file will be created in your project folder
    private static final String DB_URL = "jdbc:sqlite:journal_app.db";

    public static void main(String[] args) {
        try {
            // 1. Connect to SQLite database (creates file if not exists)
            Connection conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected to SQLite database.");

            // 2. Create tables
            createTables(conn);

            // 3. Insert a test user (like required in assignment)
            insertUser(conn, "s100201@student.fop", "p w-Stud#1", "Foo Bar");

            // 4. Fetch and display user
            fetchUser(conn, "s100201@student.fop");

            conn.close();
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    // Create users and journals tables
    public static void createTables(Connection conn) throws SQLException {
        String createUsers = """
            CREATE TABLE IF NOT EXISTS users (
                email TEXT PRIMARY KEY,
                password TEXT NOT NULL,
                display_name TEXT NOT NULL
            );
            """;

        String createJournals = """
            CREATE TABLE IF NOT EXISTS journals (
                email TEXT,
                entry_date TEXT,
                content TEXT,
                weather TEXT,
                mood TEXT,
                PRIMARY KEY (email, entry_date),
                FOREIGN KEY (email) REFERENCES users(email)
            );
            """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createUsers);
            stmt.execute(createJournals);
            System.out.println("Tables created or already exist.");
        }
    }

    // Insert a user
    public static void insertUser(Connection conn, String email, String password, String name) throws SQLException {
        String sql = "INSERT OR REPLACE INTO users (email, password, display_name) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password); // In real app, hash this!
            pstmt.setString(3, name);
            pstmt.executeUpdate();
            System.out.println("User saved: " + name);
        }
    }

    // Fetch a user by email
    public static void fetchUser(Connection conn, String email) throws SQLException {
        String sql = "SELECT display_name FROM users WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Welcome back, " + rs.getString("display_name") + "!");
            } else {
                System.out.println("User not found.");
            }
        }
    }
}
