package MainPackage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SUB_PAGE_CONTROLLER {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label subPageTitle;

    @FXML
    private TableView<Subscription> subTable;

        @FXML
        private TableColumn<Subscription, String> nameC;

        @FXML
        private TableColumn<Subscription, Number> subCountC;

        @FXML
        private TableColumn<Subscription, Number> viewsC;

        @FXML
        private TableColumn<Subscription, ?> earningsC;

            @FXML
            private TableColumn<Subscription, Number> minC;

            @FXML
            private TableColumn<Subscription, Number> maxC;

        @FXML
        private TableColumn<Subscription, String> countryC;

        @FXML
        private TableColumn<Subscription, String> dateC;

        @FXML
        private TableColumn<Subscription, Number> averageC;

    @FXML
    private Button moreDataButton;

    private Scene loginScene;
    private Controller cont;

    public void setLoginScene(Scene scene, Controller controller){
        loginScene = scene;

        cont = controller;
    }

    public void goBackClicked(MouseEvent mouseEvent) {
        cont.reset();
        Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        primaryStage.setScene(loginScene);
    }

    public HashMap<String, String> listOfSubIDs = new HashMap<String, String>();

    @FXML
    void initialize() {
        assert subPageTitle != null : "fx:id=\"subPageTitle\" was not injected: check your FXML file 'Page1.fxml'.";
        assert subTable != null : "fx:id=\"subTable\" was not injected: check your FXML file 'Page1.fxml'.";
        assert nameC != null : "fx:id=\"nameC\" was not injected: check your FXML file 'Page1.fxml'.";
        assert subCountC != null : "fx:id=\"subCountC\" was not injected: check your FXML file 'Page1.fxml'.";
        assert viewsC != null : "fx:id=\"viewsC\" was not injected: check your FXML file 'Page1.fxml'.";
        assert earningsC != null : "fx:id=\"earningsC\" was not injected: check your FXML file 'Page1.fxml'.";
        assert minC != null : "fx:id=\"minC\" was not injected: check your FXML file 'Page1.fxml'.";
        assert maxC != null : "fx:id=\"maxC\" was not injected: check your FXML file 'Page1.fxml'.";
        assert countryC != null : "fx:id=\"countryC\" was not injected: check your FXML file 'Page1.fxml'.";
        assert dateC != null : "fx:id=\"dateC\" was not injected: check your FXML file 'Page1.fxml'.";
        assert averageC != null : "fx:id=\"averageC\" was not injected: check your FXML file 'Page1.fxml'.";
        assert moreDataButton != null : "fx:id=\"moreDataButton\" was not injected: check your FXML file 'Page1.fxml'.";

    }
}
