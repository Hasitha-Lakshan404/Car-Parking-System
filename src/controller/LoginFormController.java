package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import database.ManagerDatabase;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import model.Management;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXPasswordField pwdPassword;
    public JFXButton btnCancel;
    public AnchorPane logInContext;
    public AnchorPane DashboardContext;

    public void btnLogInOnAction(ActionEvent actionEvent) throws IOException {
        for (Management m: ManagerDatabase.ManagerTable) {
            if(m.getUsername().equals(txtUserName.getText()) && m.getPassword().equals(pwdPassword.getText()) ) {
                Stage stage = (Stage) DashboardContext.getScene().getWindow();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManagementLoginDashboardForm.fxml"))));
                stage.setTitle("Management Dashboard");
                stage.centerOnScreen();
                stage.setFullScreen(true);

                Stage closeStage= (Stage) btnCancel.getScene().getWindow();
                closeStage.close();
            }else {
                txtUserName.getStyleClass().add("wrong-input");
                pwdPassword.getStyleClass().add("wrong-password");


            }
        }

    }
    public void  getContext(AnchorPane a) {
        //dashboard 1n click krddi lord wenna one lordwindow method eka.
        // aft ekee pane eka arn enne meheta(LogIn ekata) ita passe.
        // dashbord ekee pane akata anith eka lord krla
        // login eka close wenawa

        DashboardContext=a;
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
        DashboardContext.setEffect(null);
    }
}
