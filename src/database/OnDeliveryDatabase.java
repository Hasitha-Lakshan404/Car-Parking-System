package database;

import model.Delivery;

import java.util.ArrayList;

public class OnDeliveryDatabase {

    public static ArrayList<Delivery> deliveryTables = new ArrayList<Delivery>();

    static{
        deliveryTables.add(new Delivery("KA-4563","Van","Sumanasiri Herath","25/01/2022   08:22"));
        deliveryTables.add(new Delivery("58-3567","Van","Jithmal Perera","25/01/2022   09:22"));
        deliveryTables.add(new Delivery("GF-4358","Van","Awantha Fernando","25/01/2022   10:22"));
        deliveryTables.add(new Delivery("CCB-3568","Van","Sumith Kumara","25/01/2022   13:22"));
        deliveryTables.add(new Delivery("KB-3668","Cargo Lorry","Amila Pathirana","25/01/2022   15:22"));
    }

}
