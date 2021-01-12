package ro.mta.selab.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CitySelector {
    private ChoiceBox cities;
    private Map<String, ArrayList<String>> listOfCities; // for every country, there is an ArrayList of cities

    public CitySelector(String filename, ChoiceBox countries, ChoiceBox cities) {
        listOfCities = new HashMap<>();

        this.cities = cities;

        ObservableList<String> items = FXCollections.observableArrayList();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] splittedLine = line.split("\t");
                String country = splittedLine[4]; // we know that country is the 4th elem in cities.txt
                String city = splittedLine[1];
                if (!items.contains(country)) {
                    items.add(country);
                }
                ArrayList<String> updatedList = listOfCities.get(country);
                if(updatedList == null){
                    updatedList = new ArrayList<>();
                }
                updatedList.add(city);
                updatedList.sort(String::compareToIgnoreCase);
                listOfCities.put(country, updatedList);
            }
            items.sort(String::compareToIgnoreCase);
            countries.setItems(items);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateBox(String country) {
        ArrayList<String> cityList = listOfCities.get(country);
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(cityList);
        cities.setItems(items);
    }
}
