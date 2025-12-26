import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WeeklySummary {

    private List<String> moods;
    private List<String> weather;

    public WeeklySummary(List<String> moods, List<String> weather) {
        if (moods.size() != 7 || weather.size() != 7) {
            throw new IllegalArgumentException("Both moods and weather lists must have 7 entries.");
        }
        this.moods = moods;
        this.weather = weather;
    }

    public void generateSummary() {
        System.out.println("===== Weekly Summary =====\n");

        // Day-wise summary
        System.out.println("Day-wise Mood and Weather:");
        for (int i = 0; i < 7; i++) {
            System.out.println("Day " + (i + 1) + ": Mood - " + moods.get(i) + ", Weather - " + weather.get(i));
        }

        // Mood and weather overviews
        System.out.println("\nMood Overview:");
        printMoodOverview();

        System.out.println("\nWeather Overview:");
        printWeatherOverview();
    }

    private void printMoodOverview() {
        int happy = 0, sad = 0, neutral = 0;
        for (String mood : moods) {
            switch (mood.toLowerCase()) {
                case "happy": happy++; break;
                case "sad": sad++; break;
                case "neutral": neutral++; break;
            }
        }
        System.out.println("Happy days: " + happy);
        System.out.println("Sad days: " + sad);
        System.out.println("Neutral days: " + neutral);
    }

    private void printWeatherOverview() {
        int sunny = 0, rainy = 0, cloudy = 0;
        for (String w : weather) {
            switch (w.toLowerCase()) {
                case "sunny": sunny++; break;
                case "rainy": rainy++; break;
                case "cloudy": cloudy++; break;
            }
        }
        System.out.println("Sunny days: " + sunny);
        System.out.println("Rainy days: " + rainy);
        System.out.println("Cloudy days: " + cloudy);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> moods = new ArrayList<>();
        List<String> weather = new ArrayList<>();

        // Collect mood data
        System.out.println("Enter your mood for each day (Happy, Sad, Neutral):");
        for (int i = 0; i < 7; i++) {
            System.out.print("Day " + (i + 1) + ": ");
            moods.add(scanner.nextLine().trim());
        }

        // Collect weather data
        System.out.println("\nEnter the weather for each day (Sunny, Rainy, Cloudy):");
        for (int i = 0; i < 7; i++) {
            System.out.print("Day " + (i + 1) + ": ");
            weather.add(scanner.nextLine().trim());
        }

        // Generate the weekly summary
        WeeklySummary summary = new WeeklySummary(moods, weather);
        summary.generateSummary();
    }
}
