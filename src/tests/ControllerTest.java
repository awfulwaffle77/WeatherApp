package tests;

import org.junit.Before;
import org.junit.Test;
import ro.mta.selab.model.Weather;
import ro.mta.selab.model.WeatherAPI;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.mockito.plugins.MockMaker;

public class ControllerTest {
    WeatherAPI mockWAPI;
    Weather mockWeather;

    @Before
    public void setUp(){
        mockWAPI = mock(WeatherAPI.class);
        mockWeather = mock(Weather.class);
    }

    @Test
    public void initialize() {
    }

    @Test
    public void searchTemp() throws Exception {
        String city = "Baia Mare";
        String country = "RO";

        Map<String, String> toReturn = new HashMap<>();
        toReturn.put("1h", "0.19");
        toReturn.put("all", "90");

//        when(mockWAPI.getWeather(city, country)).thenReturn(toReturn);
//        assertEquals();
        when(mockWeather.get("1h")).thenReturn("0.19");
        assertEquals(mockWeather.get("1h"), toReturn.get("1h"));
        // we suppose WeatherAPI is not implemented
//        WeatherAPI weatherAPI = new WeatherAPI();
//        Weather weather = new Weather(weatherAPI.getWeather(city, country));

//        String descr = weather.get("description");
//        String capitalizedDescr = descr.substring(0, 1).toUpperCase() + descr.substring(1);
    }
}