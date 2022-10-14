package controller;

import database.InParkingDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Parking;
import tm.ParkingTM;

import java.io.IOException;

public class InParkingTableFormController {
    public AnchorPane inParkingTableContext;
    public TableView tblInParkingView;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colParkingSlot;
    public TableColumn colParkedTime;

    public void initialize(){


        colVehicleNumber.setCellValueFactory(new PropertyValueFactory("vhiNo"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("vhiType"));
        colParkingSlot.setCellValueFactory(new PropertyValueFactory("parkingSlot"));
        colParkedTime.setCellValueFactory(new PropertyValueFactory("parkedTime"));
        lordAllParking();
        
    }

    private void  lordAllParking(){
        ObservableList<ParkingTM> parkingList = FXCollections.observableArrayList();

        for (Parking p: InParkingDatabase.parkingTable) {
            ParkingTM tm=new ParkingTM(p.getVhiNo(),p.getVhiType(),p.getParkingSlot(),p.getParkedTime());
            parkingList.add(tm);
        }
        tblInParkingView.setItems(parkingList);
    }



}
