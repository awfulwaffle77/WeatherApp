package ro.mta.selab.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Parser {
    public static Map<String, String> ParseJSON(String jsonString, List<String> requiredArgs) {
        JSONObject toParse = new JSONObject(jsonString);
        Map<String, String> elemList = new HashMap<>();

        for (var arg : requiredArgs) { // goes only through the reuqested args (eg. "main", "weather")
            JSONObject obj;
            if (arg.equals("weather")) { // weather is a bit odd, and has an array at the beginning, so we parse carefully
                JSONArray array = (JSONArray) toParse.get(arg);
                obj = (JSONObject) array.get(0);
            } else {
                obj = (JSONObject) toParse.get(arg);
            }
            Iterator<String> keys = obj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                elemList.put(key, obj.get(key).toString());
            }
        }
        return elemList;
    }
}
