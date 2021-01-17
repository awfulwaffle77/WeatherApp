package ro.mta.selab.model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * Used to parse a JSON String.
 *
 * <p>Uses the static method to get the required elements from
 * the arguments. For example, temp is under main, so we request
 * main and we get all the elements there. It returns this elements
 * as a Map of String key and String value, this being the most
 * reasonable choice I have tought of.</p>
 *
 * @author awfulwaffle77
 */

public class Parser {
    public static Map<String, String> ParseJSON(String jsonString, List<String> requiredArgs) throws Exception {
        try {
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
        } catch (Exception e) { // I have forced throwing an exception here for testing purposes
            System.out.println(e.getMessage());
            throw new Exception("Error at parsing");
        }
    }
}
