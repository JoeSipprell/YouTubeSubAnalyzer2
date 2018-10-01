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
    public void start(Stage LoginStage) throws Exception{
        Parent root = FXMLLoader.load(Main.class.getResource("/LoginPage.fxml"));
        LoginStage.setTitle("YouTube Subscription Analyzer");
        LoginStage.setScene(new Scene(root, 400, 300));
        LoginStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
