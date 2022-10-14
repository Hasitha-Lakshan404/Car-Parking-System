package controller;

import database.InParkingDatabase;
import database.OnDeliveryDatabase;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import model.Delivery;
import tm.DeliveryTM;
import tm.ParkingTM;

public class OnDeliveryTableFormController {

    public AnchorPane onDeliveryTableContext;
    public TableView tblOnDeliveryView;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colDriverName;
    public TableColumn colLeftTime;

    public void initialize(){
        colVehicleNumber.setCellValueFactory(new PropertyValueFactory("vclNo"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("vclType"));
        colDriverName.setCellValueFactory(new PropertyValueFactory("driverName"));
        colLeftTime.setCellValueFactory(new PropertyValueFactory("leftTime"));
        lordAllParking();
    }

    private void  lordAllParking(){
        ObservableList<DeliveryTM> deliveryList = FXCollections.observableArrayList();

        for (Delivery d: OnDeliveryDatabase.deliveryTables) {
            DeliveryTM tm=new DeliveryTM(d.getVclNo(),d.getVclType(),d.getDriverName(),d.getLeftTime());
            deliveryList.add(tm);
        }
        tblOnDeliveryView.setItems(deliveryList);
    }
}
