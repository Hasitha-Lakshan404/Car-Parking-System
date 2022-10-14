package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import database.DriverDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Driver;
import tm.DriverTM;

import java.io.IOException;
import java.util.Optional;

public class UpdateDriverFormController extends AddDriverFormController {

    public AnchorPane updateFormContext;
    public JFXTextField txtDriverName;
    public JFXTextField txtNIC;
    public JFXTextField txtLicenseNo;
    public Button btncancel;
    public JFXTextArea txtAddress;
    public JFXTextField txtContactNo;
    public Label lblNameError;
    public Label lblNicError;
    public Label lblLicenseError;
    public Label lblAddressError;
    public Label lblContactNoError;
    public Label lblNameAlreadyExistsError;

    public void getAllData(DriverTM selectItem){
        txtDriverName.setText(selectItem.getName());
        txtNIC.setText(selectItem.getNic());
        txtLicenseNo.setText(selectItem.getLicenseNo());
        txtAddress.setText(selectItem.getAddress());
        txtContactNo.setText(selectItem.getContact());
        txtDriverName.setEditable(false);
    }

    public void btnAddDriverOnAction(ActionEvent actionEvent) {
        super.clearError();
        super.isText("");

        if(!txtAddress.getText().equals("") && !txtContactNo.getText().equals("") && !txtNIC.getText().equals("") && !txtLicenseNo.getText().equals("") ) {


            if (super.verifyAddress() && super.verifyId(txtLicenseNo.getText()) && super.verifyId(txtNIC.getText())  && super.isContactValid()) {

                Stage stage = (Stage) btncancel.getScene().getWindow();

                for (Driver data : DriverDatabase.driverTable) {
                    if (data.getName().equals(txtDriverName.getText())) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you Sure", ButtonType.YES, ButtonType.NO);
                        Optional<ButtonType> buttonType = alert.showAndWait();

                        if (buttonType.get().equals(ButtonType.YES)) {
                            data.setName(txtDriverName.getText());
                            data.setAddress(txtAddress.getText());
                            data.setNic(txtNIC.getText());
                            data.setLicenseNo(txtLicenseNo.getText());
                            data.setContact(txtContactNo.getText());

                            txtDriverName.setText("");
                            txtAddress.setText("");
                            txtNIC.setText("");
                            txtLicenseNo.setText("");
                            txtContactNo.setText("");

                            stage.close();
                        } else {

                        }
                    }
                }
            }else{
                super.ShowError();
            }
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        super.btnCancelOnAction(actionEvent);

    }


}
