package database;

import model.Driver;

import java.util.ArrayList;

public class DriverDatabase {
    public static ArrayList<Driver> driverTable = new ArrayList<Driver>();

    static {
        driverTable.add(new Driver("Sumith Kumara", "7835348345V", "B6474845", "Panadura", "0725637456"));
        driverTable.add(new Driver("Amila Pathirana", "8826253734V", "B3354674", "Galle", "0717573583"));
        driverTable.add(new Driver("Jithmal Perera", "9283289272V", "B3674589", "Horana", "0772452457"));
        driverTable.add(new Driver("Sumith Dissanayaka", "9425245373V", "B8366399", "Kaluthara", "0782686390"));
        driverTable.add(new Driver("Sumanasiri Herath", "8976544373V", "B3537538", "Beruwala", "0772534436"));
        driverTable.add(new Driver("Awantha Fernando", "9173537839V", "B3554789", "Colombo 5", "0714534356"));
        driverTable.add(new Driver("Priyanga Perera", "9135343537V", "B3853753", "Matara", "0723344562"));

    }

}
