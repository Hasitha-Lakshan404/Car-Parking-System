package controller;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import database.DriverDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Driver;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDriverFormController {
    public AnchorPane addDriverContext;
    public JFXTextField txtDriverName;
    public JFXTextField txtNIC;
    public JFXTextField txtLicenseNo;
    public Button btncancel;
    public JFXTextArea txtAddress;
    public JFXTextField txtContactNo;

    public Label lblNameAlreadyExistsError;
    public Label lblNameError;
    public Label lblNicError;
    public Label lblLicenseError;
    public Label lblAddressError;
    public Label lblContactNoError;

    public void initialize(){

    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btncancel.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagementLoginDashboardForm.fxml"))));
        stage.centerOnScreen();
        stage.close();
    }

    public void btnAddDriverOnAction(ActionEvent actionEvent) {
        clearError();
        if (verifyName() && verifyAddress() && verifyId(txtLicenseNo.getText()) && verifyId(txtNIC.getText()) && verifyNameIsExist() && isContactValid()) {
            DriverDatabase.driverTable.add(new Driver(txtDriverName.getText(), txtNIC.getText(), txtLicenseNo.getText(), txtAddress.getText(), txtContactNo.getText()));


            txtDriverName.setText("");
            txtNIC.setText("");
            txtLicenseNo.setText("");
            txtAddress.setText("");
            txtContactNo.setText("");

        } else {
            ShowError();
        }

    }


    public boolean isText(String text) {
        if (text.equals("")) {
            return false;
        }
        //if the found unicode-characters return false
        for (int i = 0; i < text.length(); i++) {
            String dta = String.valueOf(text.charAt(i));

            Pattern p = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE); //for find special character in this word
            Matcher m = p.matcher(dta);
            boolean b = m.find();

            if (b) {
                return false;
            }
        }
        return true;
    }

    public boolean isNumber() {
        //if the found number return false
        String num = txtDriverName.getText();
        char[] chars = num.toCharArray();


        for (char c : chars) {
            if (Character.isDigit(c)) {
                return false;
            }
        }

        return true;
    }

    public boolean isContactValid() {
        //if the found number return false
        String num = txtContactNo.getText();
        char[] chars = num.toCharArray();
        int loop = 0;

        for (char c : chars) {
            if (Character.isDigit(c)) {
                loop++;
            }
        }

        if (loop == 10) {
            return true;
        } else {
            return false;
        }
    }

    public boolean verifyNameIsExist() {
        for (Driver d : DriverDatabase.driverTable) {
            if (d.getName().equalsIgnoreCase(txtDriverName.getText())) {
                return false;
            }
        }
        return true;

    }

    public boolean verifyName() {
        //if number & text are true, return true
        if (isNumber() && isText(txtDriverName.getText())) {
            return true;
        }
        return false;
    }

    public boolean verifyAddress() {
        if (txtAddress.getText().equals("")) {
            return false;
        }
        if (isText(txtAddress.getText())) {
            return true;
        }

        return false;
    }

    public boolean verifyId(String x) {
        if (!x.equals("")) {
            char[] ch = x.toCharArray();
            int num = 0;
            for (int i = 0; i < x.length(); i++) {
                if (Character.isLetter(ch[i])) {
                    num++;
                } else if (Character.isDigit(ch[i])) {
                    num++;
                } else if (Character.isSpaceChar(ch[i])) {
                    return false;

                } else {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public void ShowError() {
        if (!verifyName()) {
            txtDriverName.getStyleClass().add("wrong-input");
            lblNameError.setVisible(true);
            lblNameAlreadyExistsError.setVisible(false);
        }
        if (!verifyAddress()) {
            txtAddress.getStyleClass().add("wrong-input");
            lblAddressError.setVisible(true);
        }
        if (!verifyId(txtLicenseNo.getText())) {
            txtLicenseNo.getStyleClass().add("wrong-input");
            lblLicenseError.setVisible(true);

        }
        if (!verifyId(txtNIC.getText())) {
            txtNIC.getStyleClass().add("wrong-input");
            lblNicError.setVisible(true);
        }
        if (!verifyNameIsExist()) {
            txtDriverName.getStyleClass().add("wrong-input");
            lblNameAlreadyExistsError.setVisible(true);
            lblNameError.setVisible(false);

        }
        if (!isContactValid()) {
            txtContactNo.getStyleClass().add("wrong-input");
            lblContactNoError.setVisible(true);

        }
    }

    public void clearError() {
        lblNameError.setVisible(false);
        lblNameAlreadyExistsError.setVisible(false);
        lblAddressError.setVisible(false);
        lblLicenseError.setVisible(false);
        lblNicError.setVisible(false);
        lblNameError.setVisible(false);
        lblContactNoError.setVisible(false);

    }
}
