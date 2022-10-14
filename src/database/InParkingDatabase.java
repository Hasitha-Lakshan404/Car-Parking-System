package database;

import model.Bus;
import model.Parking;

import java.util.ArrayList;

public class InParkingDatabase {
    public static ArrayList<Parking> parkingTable=new ArrayList<Parking>();

    static{
        parkingTable.add(new Parking("NA-3434","Bus","14","01/01/2022   13:14"));
        parkingTable.add(new Parking("LM-6679","Van", "1", "01/01/2022   14:11"));

    }
}
