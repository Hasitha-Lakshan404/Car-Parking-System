package controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;

public class ManagementLoginDashboardFormController {
    public AnchorPane ManagementLoginDashboardContext;
    public JFXComboBox<String> cmbSelectType;
    public AnchorPane tblChangerContext;


    public void initialize(){
        ObservableList<String> names = FXCollections.observableArrayList();
        names.addAll("In Parking","On Delivery");
        cmbSelectType.setItems(names);


        cmbSelectType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals("In Parking")){
                try {
                    Parent parent =FXMLLoader.load(getClass().getResource("../view/InParkingTableForm.fxml"));
                    tblChangerContext.getChildren().add(parent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }else if(newValue.equals("On Delivery")){
                try {
                    Parent parent =FXMLLoader.load(getClass().getResource("../view/OnDeliveryTableForm.fxml"));
                    tblChangerContext.getChildren().add(parent);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void btnAddVehicleOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent  = FXMLLoader.load(getClass().getResource("../view/AddVehicleForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Add Vehicle");
        stage.show();
        stage.centerOnScreen();
    }

     public void btnAddDriverOnAction(ActionEvent actionEvent) throws IOException {
        Parent parent  = FXMLLoader.load(getClass().getResource("../view/AddDriverForm.fxml"));
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.setTitle("Add Driver");
        stage.show();
        stage.centerOnScreen();
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) ManagementLoginDashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashboardForm.fxml"))));
        stage.setTitle("Parking System");
        stage.centerOnScreen();
        stage.setFullScreen(true);
    }

    public void lblSeeMoreOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) ManagementLoginDashboardContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/VehicleAndDriverDetailsForm.fxml"))));
        stage.centerOnScreen();
        stage.setFullScreen(true);
    }

}
