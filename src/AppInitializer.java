import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource =getClass().getResource("view/DashboardForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene =new Scene(load);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setFullScreen(true);
        primaryStage.setTitle("Parking system");
    }
}
