package database;
import model.Bus;
import model.CargoLorry;
import model.Van;
import model.Vehicle;


public class VehicleDatabase {
    public  static Vehicle []vehicleTable=new Vehicle[14];

    static {
        vehicleTable[0]=new Bus("NA-3434", "Bus",3500,60);
        vehicleTable[1]=new Van("KA-4563", "Van", 1000, 7);
        vehicleTable[2]=new Van("58-3567", "Van", 1500, 4);
        vehicleTable[3]=new Van("GF-4358", "Van", 800, 4);
        vehicleTable[4]=new Van("CCB-3568", "Van", 1800, 8);
        vehicleTable[5]=new Van("LM-6679", "Van", 1500, 4);
        vehicleTable[6]=new CargoLorry("KB-3668", "Cargo Lorry", 2500, 2);
    }
}
