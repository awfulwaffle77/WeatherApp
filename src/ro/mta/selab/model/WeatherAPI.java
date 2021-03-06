package ro.mta.selab.model;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Handles the operations needed to get the JSON response
 * from the OpenWeatherAPI.
 *
 * <p> It is constructed with the needed elements from
 * the JSON string that will be requested. The getWeather
 * method handles creating the URL and requesting the JSON
 * and then sending it to parsing.
 * </p>
 *
 * @see Parser
 *
 * @author awfulwaffle77
 */
public class WeatherAPI {
    final private String APIKey = "4a901bf01c6279df6fda7143a2d8c9e7";
    final private String baseURL = "https://api.openweathermap.org/data/2.5/weather?q=";
    final private String APPID = "&APPID=";
    private List<String> requiredArgs;

    public WeatherAPI() { // add what args you please that are matching the openweather API JSON format
        requiredArgs = new ArrayList<String>();
        requiredArgs.add("main");
        requiredArgs.add("weather");
        requiredArgs.add("wind");
    }

    public Map<String, String> getWeather(String city, String country) throws Exception {
        StringBuilder result = new StringBuilder();

        URL url = new URL(baseURL + city + "," + country + APPID + APIKey);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();

        return Parser.ParseJSON(result.toString(), requiredArgs);
    }
}
