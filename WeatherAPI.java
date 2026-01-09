/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author User
 */
package smartjournaling;

public class WeatherAPI {
    public static String getWeather() {
        API api = new API();
        
        
        String url = "https://api.data.gov.my/weather/forecast/?contains=WP%20Kuala%20Lumpur@location__location_name&sort=date&limit=1";
        
        try {
            return api.get(url);
        } catch (Exception e) {
            System.out.println("Weather Error: " + e.getMessage());
            
            return "{\"summary_forecast\":\"Unavailable\"}";
        }
    }
}

