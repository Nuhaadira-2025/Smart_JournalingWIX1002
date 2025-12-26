/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
public class WeatherService {
     public static String extractSummaryForecast(String response) {
        String key = "\"summary_forecast\":\"";
        int start = response.indexOf(key) + key.length();
        int end = response.indexOf("\"", start);

        return response.substring(start, end);
    }
}
