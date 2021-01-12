package ro.mta.selab.model;

import ro.mta.selab.model.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WeatherAPI {
    final private String APIKey = "";
    final private String baseURL = "https://api.openweathermap.org/data/2.5/weather?q=";
    final private String APPID = "&APPID=";
    private List<String> requiredArgs;

    public WeatherAPI() { // add what you please that matches the openweather API
        requiredArgs = new ArrayList<String>();
        requiredArgs.add("main");
        requiredArgs.add("weather");
    }

    public Map<String, String> getWeather(String city, String country) throws IOException {
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
