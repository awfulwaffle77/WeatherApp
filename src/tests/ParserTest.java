package tests;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ro.mta.selab.model.Parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class ParserTest {

    @Rule
    public ExpectedException e = ExpectedException.none();

    @Test
    public void parseJSON() throws Exception {
        // Example json string from https://api.openweathermap.org/data/2.5/weather?q=London,uk&APPID=APPKEYID
        String jsonString = "{\n" +
                "  \"coord\": {\n" +
                "    \"lon\": -0.1257,\n" +
                "    \"lat\": 51.5085\n" +
                "  },\n" +
                "  \"weather\": [\n" +
                "    {\n" +
                "      \"id\": 500,\n" +
                "      \"main\": \"Rain\",\n" +
                "      \"description\": \"light rain\",\n" +
                "      \"icon\": \"10d\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 300,\n" +
                "      \"main\": \"Drizzle\",\n" +
                "      \"description\": \"light intensity drizzle\",\n" +
                "      \"icon\": \"09d\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"base\": \"stations\",\n" +
                "  \"main\": {\n" +
                "    \"temp\": 282.68,\n" +
                "    \"feels_like\": 280.16,\n" +
                "    \"temp_min\": 282.59,\n" +
                "    \"temp_max\": 283.15,\n" +
                "    \"pressure\": 1016,\n" +
                "    \"humidity\": 93\n" +
                "  },\n" +
                "  \"visibility\": 5000,\n" +
                "  \"wind\": {\n" +
                "    \"speed\": 3.09,\n" +
                "    \"deg\": 260\n" +
                "  },\n" +
                "  \"rain\": {\n" +
                "    \"1h\": 0.19\n" +
                "  },\n" +
                "  \"clouds\": {\n" +
                "    \"all\": 90\n" +
                "  },\n" +
                "  \"dt\": 1610553516,\n" +
                "  \"sys\": {\n" +
                "    \"type\": 1,\n" +
                "    \"id\": 1414,\n" +
                "    \"country\": \"GB\",\n" +
                "    \"sunrise\": 1610524851,\n" +
                "    \"sunset\": 1610554630\n" +
                "  },\n" +
                "  \"timezone\": 0,\n" +
                "  \"id\": 2643743,\n" +
                "  \"name\": \"London\",\n" +
                "  \"cod\": 200\n" +
                "}";
        List<String> requiredArgs = new ArrayList<>();

        // the function should parse rain into "1h" and clouds into "all"
        requiredArgs.add("rain");
        requiredArgs.add("clouds");

        Map<String, String> expected = new HashMap<>();
        expected.put("1h", "0.19");
        expected.put("all", "90");

        Map<String, String> result = Parser.ParseJSON(jsonString, requiredArgs);

        assertEquals(result.get("1h"), expected.get("1h"));
        assertEquals(result.get("all"), expected.get("all"));
    }

    @Test
    public void parseJSONException() throws Exception {
        // checking a broken jsonString
        String jsonString = "{\"coord\":{\"lon\":-0.1257";

        // required args should not really matter
        List<String> requiredArgs = new ArrayList<>();
        requiredArgs.add("rain");

        e.expect(Exception.class);
        e.expectMessage("Error at parsing");
        Parser.ParseJSON(jsonString, requiredArgs);
    }
}