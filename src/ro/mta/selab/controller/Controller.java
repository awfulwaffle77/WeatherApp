package ro.mta.selab.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import ro.mta.selab.model.History;
import ro.mta.selab.model.Weather;
import ro.mta.selab.model.WeatherAPI;
import ro.mta.selab.view.CitySelector;

import java.awt.*;
import java.io.IOException;

public class Controller {
    public Button button;
    public ImageView weather_icon;
    public ChoiceBox box_countries;
    public ChoiceBox box_cities;
    public Label description;
    public Label wind_speed;
    public Label humidity;
    public Label temperature;
    private History history;


    @FXML
    public void initialize() { // init the choice boxes
        CitySelector citySelector = new CitySelector("src/ro/mta/selab/resources/cities.txt", box_countries, box_cities);
        history = new History("src/ro/mta/selab/history.txt");

        box_countries.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                citySelector.updateBox(box_countries.getItems().get((Integer) number2).toString());
            }
        });

    }

    public void searchTemp() throws IOException {
        String city = box_cities.getSelectionModel().getSelectedItem().toString();
        String country = box_countries.getSelectionModel().getSelectedItem().toString();

        history.writeHistory(country, city);
        WeatherAPI weatherAPI = new WeatherAPI();
        Weather weather = new Weather(weatherAPI.getWeather(city, country));

        String descr = weather.get("description");
        String capitalizedDescr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

        description.setText(capitalizedDescr);
        wind_speed.setText("Wind Speed: " + weather.get("speed")+ " m/s");
        humidity.setText("Humidity: " + weather.get("humidity") + "%");

        Image image = new Image(weather.get("icon"));
        weather_icon.setImage(image);
    }
}
