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
        /*Parent root = FXMLLoader.load(Main.class.getResource("/LoginPage.fxml"));
        LoginStage.setTitle("YouTube Subscription Analyzer");
        LoginStage.setScene(new Scene(root, 400, 300));
        LoginStage.show();*/
        //Loading the scene for entering channel URL
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/LoginPage.fxml"));
        Parent loginPage = loginLoader.load();
        Scene loginScene = new Scene(loginPage);

        //Loading scene for displaying list of subscriptions
        FXMLLoader subListLoader = new FXMLLoader(getClass().getResource("/Page1.fxml"));
        Parent subListPage = subListLoader.load();
        Scene subListScene = new Scene(subListPage);


        //designating controller for login scene
        Controller loginC = (Controller) loginLoader.getController();

        //designating controller for sub list scene
        SUB_PAGE_CONTROLLER subListC = (SUB_PAGE_CONTROLLER) subListLoader.getController();
        //providing the subList controller with the other scenes
        subListC.setLoginScene(loginScene, loginC);

        //providing the login controller with the other scenes
        loginC.setSubListScene(subListScene, subListC);

        //Showing the first screen for program
        primaryStage.setTitle("YouTube Subscription Statistics");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
