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

    public String getChannelID() {
        return channelID.get();
    }

    public SimpleStringProperty channelIDProperty() {
        return channelID;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getCountry() {
        return country.get();
    }

    public SimpleStringProperty countryProperty() {
        return country;
    }

    public String getGenre() {
        return genre.get();
    }

    public SimpleStringProperty genreProperty() {
        return genre;
    }

    public int getSubCount() {
        return subCount.get();
    }

    public SimpleIntegerProperty subCountProperty() {
        return subCount;
    }

    public long getViewCount() {
        return viewCount.get();
    }

    public SimpleLongProperty viewCountProperty() {
        return viewCount;
    }

    public long getAverageVidViews() {
        return averageVidViews.get();
    }

    public SimpleLongProperty averageVidViewsProperty() {
        return averageVidViews;
    }

    public int getUploadCount() {
        return uploadCount.get();
    }

    public SimpleIntegerProperty uploadCountProperty() {
        return uploadCount;
    }

    public double getMinInc() {
        return minInc.get();
    }

    public SimpleDoubleProperty minIncProperty() {
        return minInc;
    }

    public double getMaxInc() {
        return maxInc.get();
    }

    public SimpleDoubleProperty maxIncProperty() {
        return maxInc;
    }

    public String getDateC_string() {
        return dateC_string.get();
    }

    public SimpleStringProperty dateC_stringProperty() {
        return dateC_string;
    }

    private final SimpleStringProperty channelID;
    private final SimpleStringProperty name;
    private final SimpleStringProperty country;
    private final SimpleStringProperty genre;
    private final SimpleIntegerProperty subCount;
    private final SimpleLongProperty viewCount;
    private SimpleLongProperty averageVidViews;
    private final SimpleIntegerProperty uploadCount;
    private final SimpleDoubleProperty minInc;
    private final SimpleDoubleProperty maxInc;
    private Document socialBladeDoc;
    private Date dateC;

    //private final SimpleStringProperty
    private SimpleStringProperty dateC_string;

    /**
     * Recieves the channel id code for a youtube channel, then scrapes the social blade page for data about that channel
     *
     * @param channelID the base64 channel ID found in youtube URL
     */
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

        try {
            averageVidViews = new SimpleLongProperty(viewCount.getValue() / uploadCount.getValue());
        }catch(ArithmeticException e){
            averageVidViews = new SimpleLongProperty(-1);
        }

        country = new SimpleStringProperty(socialBladeDoc.select("#youtube-user-page-country").text());

        genre = new SimpleStringProperty(socialBladeDoc.select("#youtube-stats-header-channeltype").text());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        dateC = formatDate(socialBladeDoc);
        dateC_string = new SimpleStringProperty(df.format(dateC).toString());

        String minMax = socialBladeDoc.select("body > div:nth-child(18) > div:nth-child(4) > div:nth-child(1) > div:nth-child(2) > div:nth-child(3) > div:nth-child(1) > p:nth-child(1)").text();

        String min = minMax.substring(1, minMax.indexOf('-') - 1);
        String max = minMax.substring(minMax.lastIndexOf('$') + 1);

        minInc = calcMoney(min);
        maxInc = calcMoney(max);
    }

    /**
     * finds the date the channel was created in the document (as a string) and converts the string to a Date object
     *
     * @param socialBladeDoc the document for the scraped socialblade page
     * @return a date object for the date the channel was created
     */
    private Date formatDate(Document socialBladeDoc) {
        String dateUF = socialBladeDoc.select("#YouTubeUserTopInfoBlock > div.YouTubeUserTopInfo > span:contains(User Created) ~ span[style]").text();

        if (dateUF.length() == 13) {
            dateUF = dateUF.substring(0, 4) + "0" + dateUF.substring(4);
        }

        dateUF = dateUF.substring(0, 6) + dateUF.substring(9);

        SimpleDateFormat df = new SimpleDateFormat("MMM dd yyyy");

        Date date = null;
        try {
            date = df.parse(dateUF);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return (date);
    }//end formatDate

    /**
     * converts string to double, and multiplies by the appropriate number based on a following character
     *
     * @param unF unformatted string
     * @return number of dollars as a double
     */
    private SimpleDoubleProperty calcMoney(String unF) {
        double dMoney;

        if ("K".equals(unF.substring(unF.length() - 1))) {
            unF = unF.substring(0, unF.length() - 1);
            dMoney = Double.parseDouble(unF) * 1000;

        } else if ("M".equals(unF.substring(unF.length() - 1))) {
            unF = unF.substring(0, unF.length() - 1);
            dMoney = Double.parseDouble(unF) * 1000000;

        } else {
            dMoney = Double.parseDouble(unF);

        }

        return (new SimpleDoubleProperty(dMoney));
    }//end calcMoney

}
