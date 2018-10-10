package MainPackage;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import com.google.common.collect.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.concurrent.*;

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
        private TableColumn<Subscription, Number> upCountC;

        @FXML
        private TableColumn<Subscription, String> genreC;

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

    @FXML
    public void moreDataClicked(MouseEvent mouseEvent){
        scrapeSocialBlade();
    }

    public ArrayList<String> listOfSubIDs = new ArrayList<String>();

    ObservableList<Subscription> observableList = FXCollections.observableArrayList();

    SortedList<Subscription> channelList = new SortedList<Subscription>(observableList);

    public void setButtonText(String text){
        moreDataButton.setText(text);
    }

    public void scrapeSocialBlade() {
        //nameColumn.setCellValueFactory(cellData -> cellData.getValue().getName());
        nameC.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        subCountC.setCellValueFactory(cellData -> cellData.getValue().subCountProperty());
        viewsC.setCellValueFactory(cellData -> cellData.getValue().viewCountProperty());
        minC.setCellValueFactory(cellData -> cellData.getValue().minIncProperty());
        maxC.setCellValueFactory(cellData -> cellData.getValue().maxIncProperty());
        countryC.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        dateC.setCellValueFactory(cellData -> cellData.getValue().dateC_stringProperty());
        averageC.setCellValueFactory(cellData -> cellData.getValue().averageVidViewsProperty());
        upCountC.setCellValueFactory(cellData -> cellData.getValue().uploadCountProperty());
        genreC.setCellValueFactory(cellData -> cellData.getValue().genreProperty());

        minC.setCellFactory(new Callback<TableColumn<Subscription, Number>, TableCell<Subscription, Number>>() {
            @Override
            public TableCell<Subscription, Number> call(TableColumn<Subscription, Number> list) {
                return new MoneyFormatCell();
            }
        });

        maxC.setCellFactory(new Callback<TableColumn<Subscription, Number>, TableCell<Subscription, Number>>() {
            @Override
            public TableCell<Subscription, Number> call(TableColumn<Subscription, Number> list) {
                return new MoneyFormatCell();
            }
        });

        subCountC.setCellFactory(new Callback<TableColumn<Subscription, Number>, TableCell<Subscription, Number>>() {
            @Override
            public TableCell<Subscription, Number> call(TableColumn<Subscription, Number> list) {
                return new CommaNumberFormatCell();
            }
        });

        viewsC.setCellFactory(new Callback<TableColumn<Subscription, Number>, TableCell<Subscription, Number>>() {
            @Override
            public TableCell<Subscription, Number> call(TableColumn<Subscription, Number> list) {
                return new CommaNumberFormatCell();
            }
        });

        averageC.setCellFactory(new Callback<TableColumn<Subscription, Number>, TableCell<Subscription, Number>>() {
            @Override
            public TableCell<Subscription, Number> call(TableColumn<Subscription, Number> list) {
                return new CommaNumberFormatCell();
            }
        });

        upCountC.setCellFactory(new Callback<TableColumn<Subscription, Number>, TableCell<Subscription, Number>>() {
            @Override
            public TableCell<Subscription, Number> call(TableColumn<Subscription, Number> list) {
                return new CommaNumberFormatCell();
            }
        });


        subTable.setItems(observableList);

        //moreDataButton.setText("Loading...");
        new Thread(() -> {
            for (String channelID :
                    listOfSubIDs) {
                observableList.add(new Subscription(channelID));
            }
        }).start();


    }

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
