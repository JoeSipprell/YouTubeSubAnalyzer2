package MainPackage;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SUB_PAGE_CONTROLLER {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label subPageTitle;

    @FXML
    private TableView<?> subTable;

    @FXML
    private TableColumn<?, ?> nameC;

    @FXML
    private TableColumn<?, ?> subCountC;

    @FXML
    private TableColumn<?, ?> viewsC;

    @FXML
    private TableColumn<?, ?> earningsC;

    @FXML
    private TableColumn<?, ?> minC;

    @FXML
    private TableColumn<?, ?> maxC;

    @FXML
    private TableColumn<?, ?> countryC;

    @FXML
    private TableColumn<?, ?> dateC;

    @FXML
    private TableColumn<?, ?> averageC;

    @FXML
    private Button moreDataButton;

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
