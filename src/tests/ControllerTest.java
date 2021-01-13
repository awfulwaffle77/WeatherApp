package tests;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import ro.mta.selab.model.Parser;
import ro.mta.selab.model.Weather;
import ro.mta.selab.model.WeatherAPI;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class ControllerTest {
    WeatherAPI mockWAPI;
    Weather mockWeather;

    @Before
    public void setUp() {
        mockWAPI = mock(WeatherAPI.class);
        mockWeather = mock(Weather.class);
    }


    @Test
    public void searchTempTest1() throws Exception {
        String city = "Baia Mare";
        String country = "RO";

        when(mockWAPI.getWeather(any(String.class), any(String.class))).thenAnswer(new Answer<Map<String, String>>() {
            @Override
            public Map<String, String> answer(InvocationOnMock invocationOnMock) throws Throwable {
                Object[] args = invocationOnMock.getArguments();
                String city = (String) args[0];
                String country = (String) args[1];

                if (city.equals("Targoviste") && country.equals("RO")) {
                    Map<String, String> toReturn = new HashMap<>();
                    toReturn.put("temp", "5");
                    toReturn.put("humidity", "90");
                    return toReturn;

                } else if (city.equals("Baia Mare") && country.equals("RO")){
                    Map<String, String> toReturn = new HashMap<>();
                    toReturn.put("temp", "15");
                    toReturn.put("humidity", "72");

                    return toReturn;
                } else{
                    return null;
                }
            }
        });

        when(mockWeather.get("temp")).thenReturn("15");

        Map<String, String> map = mockWAPI.getWeather(city, country);
        if (map != null) {
            assertEquals("15", mockWeather.get("temp"));
        }

    }
}