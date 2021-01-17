package ro.mta.selab.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ro.mta.selab.model.History;
import ro.mta.selab.model.Weather;
import ro.mta.selab.model.WeatherAPI;
import ro.mta.selab.view.CitySelector;

/**
 *  Used to make all kind of connections between different classes.
 *  It gets information from Model and passes it to View to update
 *  the UI.
 *
 *  <p>Uses a WeatherAPI element and a Weather element to
 *  get information from the API and updates the UI through
 *  the FXML parameters.</p>
 *
 * @see Weather
 * @see WeatherAPI
 *
 * @author awfulwaffle77
 */
public class Controller {
    @FXML
    private Button button;
    @FXML
    private ImageView weather_icon;
    @FXML
    private ChoiceBox box_countries;
    @FXML
    private ChoiceBox box_cities;
    @FXML
    private Label description;
    @FXML
    private Label wind_speed;
    @FXML
    private Label humidity;
    @FXML
    private Label temperature;

    private History history;

    @FXML
    public void initialize() { // init the choice boxes
        CitySelector citySelector = new CitySelector("src/ro/mta/selab/resources/cities.txt", box_countries, box_cities);
        history = new History("src/ro/mta/selab/history.txt");

        // when items from first box change, update second box
        box_countries.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                citySelector.updateBox(box_countries.getItems().get((Integer) number2).toString());
            }
        });

    }

    public void searchTemp() throws Exception {
        if(box_cities.getSelectionModel().getSelectedItem() == null || box_countries.getSelectionModel().getSelectedItem() == null){
            description.setText("Please select something");
            return;
        }
        // get selected items
        String city = box_cities.getSelectionModel().getSelectedItem().toString();
        String country = box_countries.getSelectionModel().getSelectedItem().toString();

        WeatherAPI weatherAPI = new WeatherAPI();
        Weather weather = new Weather(weatherAPI.getWeather(city, country));

        history.writeHistory(country, city, weather.get("temp"), weather.get("humidity"));
        String descr = weather.get("description");
        String capitalizedDescr = descr.substring(0, 1).toUpperCase() + descr.substring(1);

        description.setText(capitalizedDescr);
        wind_speed.setText("Wind Speed: " + weather.get("speed") + " m/s");
        humidity.setText("Humidity: " + weather.get("humidity") + "%");
        temperature.setText(weather.get("temp") + "℃");

        Image image = new Image(weather.get("icon"));
        weather_icon.setImage(image);
    }
}
