package MainPackage;


import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.youtube.model.*;
import com.google.api.services.youtube.YouTube;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

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

    ArrayList<String> listOfIds = new ArrayList<String>();

    @FXML
    private Label LoginTitle;

    @FXML
    private TextField UserIDInputBox;

    @FXML
    private Button GoButton;

    @FXML
    void enterKeyPressed(KeyEvent event){
        if(event.getCode().equals(KeyCode.ENTER)){
            //nextPage();
        }
    }



    private Scene subListScene;

    private SUB_PAGE_CONTROLLER subListCont;

    /**
     * @param scene JavaFX scene for the list of channels scraped
     * @param subCont controller for the sublist scene
     */
    public void setSubListScene(Scene scene, SUB_PAGE_CONTROLLER subCont) {
        subListScene = scene;
        subListCont = subCont;
    }// end setSubListScene

    /*void nextPage() {

            checkChannel();
    }*/

    public void checkChannel(MouseEvent mouseEvent){

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

            for (String x: listOfIds) {
                System.out.println(listOfSubs.get(x) + ": " + x);
            }
            System.out.println("done");

            // switching scenes to show subList
            Stage primaryStage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
            primaryStage.setMinHeight(500);
            primaryStage.setMinWidth(1100);
            subListCont.listOfSubIDs.addAll(listOfIds);

            primaryStage.setScene(subListScene);
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
                listOfIds.add(matchID.group());
                listOfSubs.put(matchID.group(), matchTitles.group());
            }while(matchID.find() && matchTitles.find());
        }
    }

    public void reset() {

    }
}
