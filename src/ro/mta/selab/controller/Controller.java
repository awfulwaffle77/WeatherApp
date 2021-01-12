package ro.mta.selab.controller;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ro.mta.selab.model.Weather;
import ro.mta.selab.model.WeatherAPI;

import java.io.IOException;

public class Controller {
    public Label label1;
    public Button button;
    public ImageView weather_icon;

    public void handleMe() throws IOException {
        button.setText("Processing..");
        WeatherAPI weatherAPI = new WeatherAPI();
        Weather weather = new Weather(weatherAPI.getWeather("London","uk"));

        Image image = new Image(weather.get("icon"));
        weather_icon.setImage(image);
        label1.setText(weather.get("temp"));
        button.setText("Changed");
    }
}
