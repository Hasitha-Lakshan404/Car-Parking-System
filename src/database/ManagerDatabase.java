package database;

import model.Driver;
import model.Management;

import java.util.ArrayList;

public class ManagerDatabase {
    public static ArrayList<Management> ManagerTable = new ArrayList<Management>();

    static{
        ManagerTable.add(new Management("Admin","Admin","root","0715487895"));
        ManagerTable.add(new Management("hasitha Lakshan","H","123","0715879854"));
    }
}
