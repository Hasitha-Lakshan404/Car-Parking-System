package controller;

import com.jfoenix.controls.JFXTextField;

import database.OnDeliveryDatabase;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.Optional;

import static database.VehicleDatabase.vehicleTable;

public class AddVehicleFormController {
    public AnchorPane addVehicleContext;
    public JFXTextField txtVehicleNo;
    public JFXTextField txtMaximumWeight;
    public JFXTextField txtPassengers;
    public ComboBox<String> cmbVehicleType;
    public Button btncancel;

    public Label lblShowWeightError;
    public Label lblShowPassengersError;
    public Label lblShowVehicleNoError;

    public void initialize() {
        cmbVehicleType.getItems().add("Van");
        cmbVehicleType.getItems().add("Cargo Lorry");
        cmbVehicleType.getItems().add("Bus");
    }

    public void btnAddVehicleOnAction(ActionEvent actionEvent) {
        int i = 0;
        for (Vehicle v : vehicleTable) {
            if (v == null) {
                break;
            }
            i++;
        }
        if (!cmbVehicleType.getSelectionModel().isEmpty()) {
            clearError();
            //check All values are Not Null
            if(verifyNoIsExists() && verifyNumbers(txtMaximumWeight.getText()) && verifyNumbers(txtPassengers.getText()) ) {
                if(!txtVehicleNo.getText().equals("") && !txtMaximumWeight.getText().equals("") && !txtPassengers.getText().equals("")) {

                    if (cmbVehicleType.getSelectionModel().getSelectedItem().equals("Van")) {

                        int count = 0;
                        for (Vehicle v : vehicleTable) {
                            if (v == null) {
                                break;
                            }
                            if (v.getVehicleType().equals("Van")) {
                                count++;
                            }

                        }
                        if (count < Slots.van.length) {
                            vehicleTable[i] = new Van(txtVehicleNo.getText(), "Van", Integer.parseInt(txtMaximumWeight.getText()), Integer.parseInt(txtPassengers.getText()));
                            OnDeliveryDatabase.deliveryTables.add(new Delivery(txtVehicleNo.getText(), "Van", "Recently added", "Recently added"));
                            textClear();
                            showSuccessAlert("Van Added Successful!");
                        } else {
                            showAlert("The Van adding limit reached");

                        }


                    } else if (cmbVehicleType.getSelectionModel().getSelectedItem().equals("Cargo Lorry")) {

                        int count = 0;
                        for (Vehicle v : vehicleTable) {
                            if (v == null) {
                                break;
                            }
                            if (v.getVehicleType().equals("Cargo Lorry")) {
                                count++;
                            }

                        }
                        if(count<Slots.lorry.length) {
                            vehicleTable[i] = new CargoLorry(txtVehicleNo.getText(), "Cargo Lorry", Integer.parseInt(txtMaximumWeight.getText()), Integer.parseInt(txtPassengers.getText()));
                            OnDeliveryDatabase.deliveryTables.add(new Delivery(txtVehicleNo.getText(), "Cargo Lorry", "Recently added", "Recently added"));
                            textClear();
                            showSuccessAlert("Lorry Added Successful!");
                        }else{
                            showAlert("The Lorry adding limit reached");
                        }
                    } else if (cmbVehicleType.getSelectionModel().getSelectedItem().equals("Bus")) {

                        boolean yes = true;
                        for (Vehicle v : vehicleTable) {
                            if (v == null) {
                                break;
                            }
                            if (v.getVehicleType().equals("Bus")) {
                                yes = false;

                                showAlert("The Bus adding limit reached");

                            }

                        }
                        if (yes) {
                            vehicleTable[i] = new Bus(txtVehicleNo.getText(), "Bus", Integer.parseInt(txtMaximumWeight.getText()), Integer.parseInt(txtPassengers.getText()));
                            OnDeliveryDatabase.deliveryTables.add(new Delivery(txtVehicleNo.getText(), "Bus", "Recently added", "Recently added"));
                            textClear();

                            showSuccessAlert("Bus Added Successful!");
                        }

                    }
                }else{
                    showAlert("Something went wrong!!");
                }
            }else{
                showError();
            }

        } else {

            showAlert("Plz! Select a Vehicle Type");

        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btncancel.getScene().getWindow();
        stage.centerOnScreen();
        stage.close();
    }

    private boolean verifyNumbers(String x) {
        //return true if the txt have number
        char[] ch = x.toCharArray();
        int num = 0;
        for (int i = 0; i < x.length(); i++) {
            if (Character.isLetter(ch[i])) {
                return false;
            } else if (Character.isDigit(ch[i])) {
                num++;
            } else if (Character.isSpaceChar(ch[i])) {
                return false;

            } else {
                return false;
            }
        }
        return true;
    }
    public boolean verifyNoIsExists() {
        for(int i=0;i<vehicleTable.length;i++){
            if(vehicleTable[i]!=null){
                if(vehicleTable[i].getVehicleNo().equalsIgnoreCase(txtVehicleNo.getText())){
                    return false;
                }
            }
        }
        return true;
    }

    public void showError() {
        if (!verifyNoIsExists()) {
            txtVehicleNo.getStyleClass().add("wrong-input");
            lblShowVehicleNoError.setVisible(true);

        }
        if (!verifyNumbers(txtMaximumWeight.getText())) {
            txtMaximumWeight.getStyleClass().add("wrong-input");
            lblShowWeightError.setVisible(true);
        }
        if (!verifyNumbers(txtPassengers.getText())) {
            txtPassengers.getStyleClass().add("wrong-input");
            lblShowPassengersError.setVisible(true);
        }
    }

    public void clearError() {
        lblShowVehicleNoError.setVisible(false);
        lblShowWeightError.setVisible(false);
        lblShowPassengersError.setVisible(false);
        txtMaximumWeight.getStyleClass().add("DarkTheme.css");
    }
    private void showAlert(String contentText){
        Alert alert = new Alert(Alert.AlertType.ERROR, contentText, ButtonType.OK);
        alert.setTitle("Add Vehicle");
        alert.setHeaderText("Un-Successful");
        alert.show();
    }
    private void textClear(){
        txtVehicleNo.setText("");
        txtMaximumWeight.setText("");
        txtPassengers.setText("");
    }

    private void showSuccessAlert(String contentText){
        Alert alert = new Alert(Alert.AlertType.INFORMATION, contentText, ButtonType.OK);
        alert.setTitle("Add Vehicle");
        alert.setHeaderText("Successful");
        Optional<ButtonType>button =alert.showAndWait();

        if(button.get().equals(ButtonType.OK)){
            Stage stage = (Stage) btncancel.getScene().getWindow();
            stage.centerOnScreen();
            stage.close();
        }
    }
}
