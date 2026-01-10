package smartjournaling;

import java.time.LocalTime;

public class WeatherAPI {

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

            
            String result = extractValue(jsonResponse, targetField);
            
            
            if (result.equals("Unknown")) {
                System.out.println(">>> Time specific failed, switching to Summary...");
                result = extractValue(jsonResponse, "summary_forecast");
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
