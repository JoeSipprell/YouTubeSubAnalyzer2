package MainPackage;

// Youtube api key
// AIzaSyAAgb8WcoH5t07CX0gfTFxJFy5XMZIWmDk

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Main.class.getResource("/Page1.fxml"));
        primaryStage.setTitle("YouTube Subscription Analyzer");
        primaryStage.setScene(new Scene(root, 1100, 500));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
