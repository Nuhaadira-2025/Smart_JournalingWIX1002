package smartjournaling;

import java.time.LocalTime;

public class WeatherAPI {

   
    private static String simplifyWeather(String rawWeather) {
        if (rawWeather == null || rawWeather.equals("Unknown")) return "Unavailable";

        // 1. Clean the input (Remove spaces, ignore case)
        String w = rawWeather.trim().toLowerCase();

        
        // Priority 1: Check for "No Rain" or "Clear"
        if (w.contains("tiada") || w.contains("no rain") || w.contains("cerah") || w.contains("clear")) {
            return "Sunny";
        }
        
        // Priority 2: Check for Rain, Storms, or Thunder
        else if (w.contains("hujan") || w.contains("rain") || w.contains("ribut") || w.contains("storm")) {
            return "Rainy";
        }
        
        // Priority 3: Check for Clouds
        else if (w.contains("berawan") || w.contains("cloud")) {
            return "Cloudy";
        }

       
        return rawWeather;
    }

    public static String getWeather() {
        API api = new API();
        String url = "https://api.data.gov.my/weather/forecast/?contains=WP%20Kuala%20Lumpur@location__location_name&sort=date&limit=1";
        
        try {
            // Get the Raw Data
            String jsonResponse = api.get(url);

            // Decide based on time (Morning / Afternoon / Night)
            int currentHour = LocalTime.now().getHour();
            String targetField;
            
            if (currentHour < 12) {
                targetField = "morning_forecast";
            } else if (currentHour < 18) {
                targetField = "afternoon_forecast";
            } else {
                targetField = "night_forecast";
            }

            // Extract the raw Malay text first
            String rawResult = extractValue(jsonResponse, targetField);
            
            // Fallback to summary if specific time is missing
            if (rawResult.equals("Unknown")) {
                System.out.println(">>> Time specific failed, switching to Summary...");
                rawResult = extractValue(jsonResponse, "summary_forecast");
            }

            // Apply the "Extra Feature" mapping rule
            return simplifyWeather(rawResult);

        } catch (Exception e) {
            System.out.println("Weather Error: " + e.getMessage());
            return "Unavailable"; 
        }
    }

    
    private static String extractValue(String json, String key) {
        try {
            int keyIndex = json.indexOf("\"" + key + "\"");
            if (keyIndex == -1) return "Unknown";

            int colonIndex = json.indexOf(":", keyIndex);
            int startQuote = json.indexOf("\"", colonIndex + 1);
            int endQuote = json.indexOf("\"", startQuote + 1);

            return json.substring(startQuote + 1, endQuote);
            
        } catch (Exception e) {
            return "Unknown";
        }
    }
}
