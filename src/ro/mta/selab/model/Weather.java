package ro.mta.selab.model;

import java.util.Map;

public class Weather {
    Map<String, String> weatherObject;

    public Weather(Map<String, String> map) {
        // change the temp from K to Celsius
        if(map.get("temp") != null) {
            map.put("temp", String.valueOf((int) Double.parseDouble(map.get("temp")) - 273));
        }
        if(map.get("feels_like") != null) {
            map.put("feels_like", String.valueOf((int) Double.parseDouble(map.get("feels_like")) - 273));
        }
        if(map.get("temp_min") != null) {
            map.put("temp_min", String.valueOf((int) Double.parseDouble(map.get("temp_min")) - 273));
        }
        if(map.get("temp_max") != null) {
            map.put("temp_max", String.valueOf((int) Double.parseDouble(map.get("temp_max")) - 273));
        }

        // setting the icon link
        if(map.get("icon") != null) {
            map.put("icon", "http://openweathermap.org/img/w/" + map.get("icon") + ".png");
        }

        weatherObject = map;
    }

    public String get(String key){
        return weatherObject.get(key);
    }
}
