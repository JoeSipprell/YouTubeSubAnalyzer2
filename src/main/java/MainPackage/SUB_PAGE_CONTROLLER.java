package MainPackage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.SubscriptionListResponse;
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

    /** Application name. */
    final String APPLICATION_NAME = "YouTubeSubAnalyzer";

    /**
     * Define a global variable that identifies the name of a file that
     * contains the developer's API key.
     */
    final String PROPERTIES_FILENAME = "youtube.properties";

    /** Global instance of the {@link FileDataStoreFactory}. */
    //private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    HttpTransport HTTP_TRANSPORT;

    String channelID = "UCb81rLqF7RVbnqmOEO0IGMg";
    //String channelID = "UC_YqzcTtBqrVCbfUwzPlYaw";

    YouTube youtube;

    HashMap<String, String> listOfSubs = new HashMap<String, String>();

    @FXML
    private URL location;

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

    @FXML
    private TextField UserIDInputBox;

    @FXML
    private Button GoButton;

    @FXML
    public void moreDataClicked(MouseEvent mouseEvent){
        //this is where a page with more statistics would load
    }

    public ArrayList<String> listOfSubIDs = new ArrayList<String>();

    ObservableList<Subscription> observableList = FXCollections.observableArrayList();

    SortedList<Subscription> channelList = new SortedList<Subscription>(observableList);


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

    @FXML
    public void goButtonClicked(MouseEvent mouseEvent) {
        scrapeYouTubeChannelIDs(mouseEvent);
    }

    public void scrapeYouTubeChannelIDs(MouseEvent mouseEvent){

        Properties properties = new Properties();
        try {
            InputStream in = Controller.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);
        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }

        HTTP_TRANSPORT = new NetHttpTransport();
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName(APPLICATION_NAME).build();

        String apiKey = properties.getProperty("youtube.apikey");

        try{
            HashMap<String, String> parameters = new HashMap<String, String>();
            parameters.put("part", "snippet");
            parameters.put("channelId", channelID);

            YouTube.Subscriptions.List subPage = youtube.subscriptions().list(parameters.get("part").toString());
            if (parameters.containsKey("channelId") && parameters.get("channelId") != "") {
                subPage.setChannelId(parameters.get("channelId"));
            }

            subPage.setKey(apiKey);

            subPage.setFields("items(snippet(resourceId/channelId,title)),nextPageToken");

            SubscriptionListResponse response = subPage.execute();
            //System.out.println(response);

            addSubs(response);

            do{
                subPage.setPageToken(response.getNextPageToken());

                response = subPage.execute();

                addSubs(response);
            }
            while(response.getNextPageToken() != null);
            System.out.println(response);

            for (String x: listOfSubIDs) {
                System.out.println(listOfSubs.get(x) + ": " + x);
            }
            System.out.println("done");

            scrapeSocialBlade();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    public void addSubs(SubscriptionListResponse response){

        Pattern findIDs = Pattern.compile("(?<=channelId\":\")(.*?)(?=\"})");
        Matcher matchID = findIDs.matcher(response.toString());

        Pattern findTitles = Pattern.compile("(?<=title\":\")(.*?)(?=\"})");
        Matcher matchTitles = findTitles.matcher(response.toString());


        if (matchID.find() && matchTitles.find()) {
            do{
                listOfSubIDs.add(matchID.group());
                listOfSubs.put(matchID.group(), matchTitles.group());
            }while(matchID.find() && matchTitles.find());
        }
    }
}
