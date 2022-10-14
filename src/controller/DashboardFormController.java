package controller;

import com.jfoenix.controls.JFXButton;
import database.DriverDatabase;
import database.InParkingDatabase;
import database.OnDeliveryDatabase;
import database.VehicleDatabase;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

import static database.VehicleDatabase.vehicleTable;

public class DashboardFormController {
    public AnchorPane DashboardContext;
    public ComboBox<String> cmbSelectVehicleNo;
    public Label lblVehicleType;
    public ComboBox<String> cmbSelectDriver;
    public Label lblSlotNumberFrame;
    public Label lblTime;
    public Label lblDate;
    public Label lblParkingSlotNumber;
    public JFXButton btnParkVehicle;
    public JFXButton btnOnDelivery;

    public void initialize() {

        //Add Data to ComboBoxes
        ObservableList<String> vNo = FXCollections.observableArrayList();

        for (Vehicle v : VehicleDatabase.vehicleTable) {
            if (v != null) {
                vNo.add(v.getVehicleNo());
            }
        }
        cmbSelectVehicleNo.setItems(vNo);

        //Add Driver Names to ComboBox
        ObservableList<String> dName = FXCollections.observableArrayList();
        for (Driver d : DriverDatabase.driverTable) {
            dName.add(d.getName());
        }
        cmbSelectDriver.setItems(dName);

        cmbSelectVehicleNo.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            for (Vehicle v : vehicleTable) {
                if (v != null) {
                    if (newValue.equals(v.getVehicleNo())) {
                        //set lblVehicleName
                        lblVehicleType.setText(v.getVehicleType());

                        boolean notPark = true;
                        boolean notDelivery = true;
                        for (Parking p : InParkingDatabase.parkingTable) {
                            if (p.getVhiNo().equals(v.getVehicleNo())) {
                                notPark = false;

                            }
                        }
                        for (Delivery d : OnDeliveryDatabase.deliveryTables) {
                            if (d.getVclNo().equals(v.getVehicleNo())) {
                                notDelivery = false;
                                break;
                            }
                        }

                        if (notPark) {
                            searchSlot();
                            btnParkVehicle.setDisable(false); //Parking Button is Activated
                            btnOnDelivery.setDisable(true);
                            cmbSelectDriver.setDisable(true);
                        }
                        if (notDelivery) {
                            lblParkingSlotNumber.setText("0");
                            btnParkVehicle.setDisable(true);
                            btnOnDelivery.setDisable(false); //Delivery Button is Activated
                            cmbSelectDriver.setDisable(false);
                        }

                    }
                }

            }
        });

        //RealTime Clock
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            LocalDate currentDate = LocalDate.now();
            lblTime.setText(currentTime.getHour() + ":" + currentTime.getMinute() + ":" + currentTime.getSecond());
            lblDate.setText(currentDate.getDayOfMonth() + "-" + currentDate.getMonthValue() + "-" + currentDate.getYear());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();


    }

    public void btnParkVehicleOnAction(ActionEvent actionEvent) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   HH:mm");
        Date date = new Date();


        if (!cmbSelectVehicleNo.getSelectionModel().isEmpty()  && !lblVehicleType.getText().equals("")) {
            InParkingDatabase.parkingTable.add(new Parking(cmbSelectVehicleNo.getSelectionModel().getSelectedItem(), lblVehicleType.getText(), lblParkingSlotNumber.getText(), formatter.format(date)));

            for (Delivery d : OnDeliveryDatabase.deliveryTables) {
                if (d.getVclNo().equals(cmbSelectVehicleNo.getSelectionModel().getSelectedItem()) && d.getVclType().equals(lblVehicleType.getText())) {
                    OnDeliveryDatabase.deliveryTables.remove(d);
                    break;
                }
            }
            lblParkingSlotNumber.setText("");
            lblVehicleType.setText("");

            BoxBlur blur = new BoxBlur(4, 4, 4);
            getSuccessfulAlert("Now you can park...",blur,"Parking");

        } else {
            getErrorAlert("Something went Wrong","Parking");
        }

    }

    public void OnDeliveryShiftOnAction(ActionEvent actionEvent) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy   HH:mm");
        Date date = new Date();

        if (!cmbSelectDriver.getSelectionModel().isEmpty() && !cmbSelectVehicleNo.getSelectionModel().isEmpty() && !lblVehicleType.getText().equals("")) {

            boolean notExists = true;
              for (Delivery d:OnDeliveryDatabase.deliveryTables) {
                if(cmbSelectDriver.getSelectionModel().getSelectedItem().equals(d.getDriverName())){ //Search driver is gone
                    notExists=false;
                }
            }
            if(notExists) {
                OnDeliveryDatabase.deliveryTables.add(new Delivery(
                        cmbSelectVehicleNo.getSelectionModel().getSelectedItem(),
                        lblVehicleType.getText(),
                        cmbSelectDriver.getSelectionModel().getSelectedItem(),
                        formatter.format(date)));


                for (Parking p : InParkingDatabase.parkingTable) {
                    if (p.getVhiNo().equalsIgnoreCase(cmbSelectVehicleNo.getSelectionModel().getSelectedItem()) && p.getVhiType().equalsIgnoreCase(lblVehicleType.getText())) {
                        InParkingDatabase.parkingTable.remove(p);
                        break;
                    }
                }

                lblParkingSlotNumber.setText("");
                lblVehicleType.setText("");

                BoxBlur blur = new BoxBlur(4, 4, 4);
                getSuccessfulAlert("Now you can leave...",blur,"Deliver");

            }else {
                getErrorAlert("Driver Name Invalid","Deliver");
            }


        } else {

            getErrorAlert("Something went Wrong","Deliver");

        }
    }


    public void managementLoginOnAction(ActionEvent actionEvent) throws IOException {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        DashboardContext.setEffect(blur);
        lordWidow("../view/loginForm.fxml", "Management LogIN"); //logout
    }

    public void lordWidow(String location, String title) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource(location));
        Parent parent = loader.load();

        LoginFormController controller = loader.getController(); //logout eka open wena eka
        controller.getContext(DashboardContext); //dash apn-> oyage logout

        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setTitle(title);
        stage.setScene(new Scene(parent));
        stage.show();
        stage.centerOnScreen();

    }

    private void getSuccessfulAlert(String contentText,BoxBlur blur,String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION,contentText, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText("Successful");
        DashboardContext.setEffect(blur);
        Optional<ButtonType> button = alert.showAndWait();

        if (button.get().equals(ButtonType.OK)) {
            DashboardContext.setEffect(null);
        }
    }

    private void getErrorAlert(String contentText,String title){
        Alert alert = new Alert(Alert.AlertType.ERROR, contentText, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText("Un-Successful");
        alert.show();
    }

    private void searchSlot() {
        if (lblVehicleType.getText().equals("Van")) {

            for (int i = 0; i < Slots.van.length; i++) {
                boolean yes = true;

                for (Parking p : InParkingDatabase.parkingTable) {
                    if (p.getParkingSlot().equalsIgnoreCase(String.valueOf(Slots.van[i]))) {
                        yes = false;
                        break;
                    }
                }

                if (yes) {
                    lblParkingSlotNumber.setText(String.valueOf(Slots.van[i]));
                    return;
                }
            }



        } else if (lblVehicleType.getText().equals("Cargo Lorry")) {
            for (int i = 0; i < Slots.lorry.length; i++) {
                boolean yes = true;

                for (Parking p : InParkingDatabase.parkingTable) {
                    if (p.getParkingSlot().equalsIgnoreCase(String.valueOf(Slots.lorry[i]))) {
                        yes = false;
                        break;
                    }
                }

                if (yes) {
                    lblParkingSlotNumber.setText(String.valueOf(Slots.lorry[i]));
                    return;
                }
            }


        } else if (lblVehicleType.getText().equals("Bus")) {
            lblParkingSlotNumber.setText("14");

        }
    }



}
