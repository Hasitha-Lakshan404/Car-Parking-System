package controller;

import com.jfoenix.controls.JFXButton;
import database.DriverDatabase;
import database.InParkingDatabase;
import database.OnDeliveryDatabase;
import database.VehicleDatabase;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Delivery;
import model.Driver;
import model.Parking;
import model.Vehicle;
import tm.DriverTM;
import tm.VehicleTM;

import java.io.IOException;
import java.util.Optional;

import static database.VehicleDatabase.vehicleTable;

public class VehicleAndDriverDetailsFormController {
    public AnchorPane VehicleDriverDetailsContext;
    public TableView tblIDriverDetailsView;
    public TableColumn colDriverName;
    public TableColumn colDriverNic;
    public TableColumn colDrivingLicense;
    public TableColumn colAddress;
    public TableColumn colContactNo;
    public TableView tblIVehcleDetailsView;
    public TableColumn colVehicleNumber;
    public TableColumn colVehicleType;
    public TableColumn colMaximumWeight;
    public TableColumn colNoOfPassengers;

    public JFXButton btnCancel;
    public FontAwesomeIconView btnIconView;
    public FontAwesomeIconView imageviewRefresh;

    public void initialize()    {

        colDriverName.setCellValueFactory(new PropertyValueFactory("name"));
        colDriverNic.setCellValueFactory(new PropertyValueFactory("nic"));
        colDrivingLicense.setCellValueFactory(new PropertyValueFactory("licenseNo"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colContactNo.setCellValueFactory(new PropertyValueFactory("contact"));

        colVehicleNumber.setCellValueFactory(new PropertyValueFactory("vehicleNo"));
        colVehicleType.setCellValueFactory(new PropertyValueFactory("vehicleType"));
        colMaximumWeight.setCellValueFactory(new PropertyValueFactory("weight"));
        colNoOfPassengers.setCellValueFactory(new PropertyValueFactory("noOfPassengers"));

        loadAllDrivers();
        loadAllVehicles();
    }

    public void menuEditOnAction(ActionEvent actionEvent) throws IOException {
        lordWidow("../view/UpdateDriverForm.fxml","Update Driver");
        btnIconView.setGlyphName("REFRESH");
    }

    public void menuDeleteOnAction(ActionEvent actionEvent) {
        DriverTM selectItem= (DriverTM) tblIDriverDetailsView.getSelectionModel().getSelectedItem();


        for (Driver d:DriverDatabase.driverTable) {
            if(selectItem.getName().equals(d.getName()) && selectItem.getNic().equals(d.getNic())  && selectItem.getLicenseNo().equals(d.getLicenseNo()) && selectItem.getAddress().equals(d.getAddress())){

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you Sure you want to Delete?", ButtonType.YES,ButtonType.NO);
                alert.setTitle("Delete");
                alert.setHeaderText("Delete Driver!!");
                Optional<ButtonType> buttonType=alert.showAndWait();

                if(buttonType.get().equals(ButtonType.YES)){
                    DriverDatabase.driverTable.remove(d);
                    loadAllDrivers();
                    break;
                }

            }
        }
    }

    public void btnCloseOnAction(ActionEvent actionEvent) throws IOException {



        if (btnIconView.getGlyphName().equals("REFRESH")){
            loadAllDrivers();
            loadAllVehicles();
            btnIconView.setGlyphName("CLOSE");
        }else {
            Stage stage = (Stage) VehicleDriverDetailsContext.getScene().getWindow();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagementLoginDashboardForm.fxml"))));
            stage.centerOnScreen();
            stage.setFullScreen(true);
        }
    }

    private void loadAllDrivers() {
        ObservableList<DriverTM> driverList = FXCollections.observableArrayList();

        for (Driver d : DriverDatabase.driverTable) {
            DriverTM Dtm = new DriverTM(d.getName(), d.getNic(), d.getLicenseNo(), d.getAddress(), d.getContact());
            driverList.add(Dtm);
        }
        tblIDriverDetailsView.setItems(driverList);
    }

    private void loadAllVehicles() {
        ObservableList<VehicleTM> vehicleList = FXCollections.observableArrayList();

        for (Vehicle v : vehicleTable) {
            if(v!=null){
                VehicleTM Vtm = new VehicleTM(v.getVehicleNo(), v.getVehicleType(), v.getWeight(), v.getNoOfPassengers());
                vehicleList.add(Vtm);
            }
        }
        tblIVehcleDetailsView.setItems(vehicleList);
    }



    public void lordWidow(String location,String title) throws IOException {

        DriverTM selectItem= (DriverTM)  tblIDriverDetailsView.getSelectionModel().getSelectedItem();


        FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
        Parent parent= loader.load();

        UpdateDriverFormController controller =loader.getController();

        controller.getAllData(selectItem);
        Stage stage =new Stage(StageStyle.UNDECORATED);
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();

    }



    public void menuVehicleDeleteOnAction(ActionEvent actionEvent) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Are you Sure you want to Delete?", ButtonType.YES,ButtonType.NO);
        alert.setTitle("Delete");
        alert.setHeaderText("Delete Vehicle!!");
        Optional<ButtonType> buttonType=alert.showAndWait();

        if(buttonType.get().equals(ButtonType.YES)) {

            VehicleTM selectItem = (VehicleTM) tblIVehcleDetailsView.getSelectionModel().getSelectedItem();

            for (int i = 0; i < vehicleTable.length; i++) {
                if (vehicleTable[i].getVehicleNo() != null) {
                    if (selectItem.getVehicleNo().equals(vehicleTable[i].getVehicleNo())) {

                        for (int j = i; j < vehicleTable.length - 1; j++) {
                            vehicleTable[j] = vehicleTable[j + 1];

                        }
                        break;
                    }

                }
            }

            //remove parking,delivery vehicles from parking table & delivery table
            for (Delivery d : OnDeliveryDatabase.deliveryTables) {
                if (selectItem.getVehicleNo().equals(d.getVclNo())) {
                    OnDeliveryDatabase.deliveryTables.remove(d);
                    break;
                }
            }

            for (Parking p : InParkingDatabase.parkingTable) {
                if (selectItem.getVehicleNo().equals(p.getVhiNo())) {
                    InParkingDatabase.parkingTable.remove(p);
                    break;
                }
            }


            loadAllVehicles();
        }
    }


}
