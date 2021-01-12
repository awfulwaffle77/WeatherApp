package ro.mta.selab.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.time.LocalDate;

public class History {
    String filename;

    public History(String filename) {
        this.filename = filename;
    }

    public void writeHistory(String country, String city) { // it is time.. to writeHistory
        try {
            FileWriter myWriter = new FileWriter(filename, true);
            LocalDate date = LocalDate.now();
            myWriter.write("[" + date.getYear() + "-" + date.getMonthValue() + "-" + date.getDayOfMonth() +  "]" + " " + country + " " + city + "\n");
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

}
