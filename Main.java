/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author User
 */
public class Main {

    public static void main(String[] args) {
        // Step 1: Call the weather API
        String response = WeatherAPI.getWeather();

        // Step 2: Extract weather summary
        String weatherToday = WeatherService.extractSummaryForecast(response);

        // Step 3: Display result
        System.out.println("Today's Weather:");
        System.out.println(weatherToday);
    }
    
}
