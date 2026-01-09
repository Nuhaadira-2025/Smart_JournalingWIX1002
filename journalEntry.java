package smartjournaling;

public class JournalEntry {
    private String email, date, content, mood, weather;

    public JournalEntry(String email, String date, String content, String mood, String weather) {
        this.email = email;
        this.date = date;
        this.content = content;
        this.mood = mood;
        this.weather = weather;
    }

    public String getEmail() { return email; }
    public String getDate() { return date; }
    public String getContent() { return content; }
    public String getMood() { return mood; }
    public String getWeather() { return weather; }
}
