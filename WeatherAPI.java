package smartJournaling;

import java.util.HashMap;
import java.util.Map;
import java.time.LocalTime;

public class WeatherAPI {

    private static String translateToEnglish(String malayText) {
    if (malayText == null) return "Unavailable";

    malayText = malayText.toLowerCase();

    if (malayText.contains("ribut")) return "Thunderstorms";
    if (malayText.contains("hujan lebat")) return "Heavy rain";
    if (malayText.contains("hujan ringan")) return "Light rain";
    if (malayText.contains("hujan")) return "Rain";
    if (malayText.contains("berawan")) return "Cloudy";
    if (malayText.contains("cerah")) return "Sunny";
    if (malayText.contains("kabut")) return "Foggy";
    if (malayText.contains("angin")) return "Strong wind";
    if (malayText.contains("panas")) return "Hot";
    if (malayText.contains("sejuk")) return "Cool";

    return malayText; // fallback if no keyword matched
}


    public static String getWeather() {
        API api = new API();
        String url = "https://api.data.gov.my/weather/forecast/?contains=WP%20Kuala%20Lumpur@location__location_name&sort=date&limit=1";
        

        try {
            //Get the Raw Data
            String jsonResponse = api.get(url);

            //Decide based on time (Morning / Afternoon / Night)
            int currentHour = LocalTime.now().getHour();
            String targetField;

            if (currentHour < 12) {
                targetField = "morning_forecast";
            } else if (currentHour < 18) {
                targetField = "afternoon_forecast";
            } else {
                targetField = "night_forecast";
            }


            String result = translateToEnglish(extractValue(jsonResponse, targetField)); // ← LINE 28 (updated)

            if (result.equals("Unknown")) {
                System.out.println(">>> Time specific failed, switching to Summary...");
                result = translateToEnglish(extractValue(jsonResponse, "summary_forecast")); // ← LINE 33 (updated)
            }
            return result;

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
