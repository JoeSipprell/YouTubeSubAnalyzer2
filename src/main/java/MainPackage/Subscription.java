package MainPackage;

import javafx.beans.property.*;
import javafx.beans.value.ObservableLongValue;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Subscription {

    private final SimpleStringProperty channelID;
    private final SimpleStringProperty name;
    /*private final SimpleStringProperty country;
    private final SimpleStringProperty genre;*/
    private final SimpleIntegerProperty subCount;
    private final SimpleLongProperty viewCount;
    private final SimpleLongProperty averageVidViews;
    private final SimpleIntegerProperty uploadCount;
    /*private final SimpleDoubleProperty minInc;
    private final SimpleDoubleProperty maxInc;
    private String tableRow;*/
    private Document socialBladeDoc;

    public Subscription(String channelID)
    {
        this.channelID = new SimpleStringProperty(channelID);
        String socialBladeURL = "https://socialblade.com/youtube/channel/" + channelID;

        try {
            socialBladeDoc = Jsoup.connect(socialBladeURL).userAgent("Chrome/66.0.3359.139").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        name = new SimpleStringProperty( socialBladeDoc.select("#YouTubeUserTopInfoBlockTop > div:nth-child(1) > h1").first().text());

        subCount = new SimpleIntegerProperty(Integer.parseInt(socialBladeDoc.select("#youtube-stats-header-subs").text()));

        viewCount = new SimpleLongProperty(Long.parseLong(socialBladeDoc.select("#youtube-stats-header-views").text()));

        uploadCount = new SimpleIntegerProperty(Integer.parseInt(socialBladeDoc.select("#youtube-stats-header-uploads").text()));

        averageVidViews = new SimpleLongProperty(viewCount.getValue() / uploadCount.getValue());


    }

}
