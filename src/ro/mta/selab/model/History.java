package ro.mta.selab.model;

import java.io.DataInput;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

public class History {
    private String filename;

    public History(String filename) {
        this.filename = filename;
    }

    public void writeHistory(String country, String city, String temp, String humidiy) { // it is time.. to writeHistory
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
            myWriter.write("[" + date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() +
                    " " + time.getHour() + ":" + time.getMinute() + "]" +
                    " " + country + " " + city + " " + temp + "℃ " + "Humidity " + humidiy + "%\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
