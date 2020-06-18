package pp201920.project.a5;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
*
* The ActivityParser class contains the methods necessary to 
* parse the json passed in it's constructor into smaller 
* {@link Activity} objects and fill the ArrayList passed 
* in it's constructor with these {@link Activity} objects.
*
* @author  S. Balawal
* @author  A. Nicoletti
* @author R.Zorzi
* @version 1.0
*/

public class ActivityParser{

    final Logger logging = LogManager.getLogger();
    final MyLogger logger = new MyLogger(logging);
    
    ArrayList<Activity> list;
    JsonArray Items;

    /**
     * Default consructor for the ActivityParser class
     */
    public ActivityParser(){
        super();
    }

    /**
     * The constructor ActivityParser used in the {@link App} class.
     * 
     * @param list will be filled with {@link Activity} objects
     * @param json is the json to be parsed
     */
    public ActivityParser(ArrayList<Activity> list, String json){
        this.list = list;
        this.Items = getItems(json);
    }

    /**
     * The parseAndFillActivityList parses the 
     * <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonObject.html">JsonObjects</a>
     * in the <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonArray.html">JsonArray</a> Items
     * and fills the ArrayList list with {@link Activity} objects.
     *  
     */
    public void parseAndFillActivityList(){
        for (JsonElement item : this.Items) {

            Activity activity = parseJsonObject(
                item.getAsJsonObject()
            );

            if(activity != null)
                list.add(activity);
        }
    }

    /**
     * The getItems method takes a json string as parameter
     * and extracts the Items array from it.
     * 
     * @param json is the json passed in the constructor of this class
     * @return returns a <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonArray.html">JsonArray</a>
     * containig various <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonObject.html">JsonObjects</a>.
     */
    public JsonArray getItems(String json){
        JsonObject jsonResponse = new JsonParser().
                                      parse(json).
                                      getAsJsonObject();

        return jsonResponse.get("Items").getAsJsonArray();
    }


    /**
     * The parseJsonObject method parses a 
     * <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonObject.html">JsonObject</a>
     * into an {@link Activity} object. To do this it divides the main JsonObject passed as a parameter into smaller JsonObjects
     * and calls the {@link #checkIfActivityHasGpsTracking(JsonObject) checkIfActivityHasGpsTracking}, {@link #getDescription(JsonObject) getDescription},
     * {@link #getRegionData(JsonObject, String) getRegionData} and {@link #getLanguage(JsonObject) getLanguage} methods to extract the 
     * data from these smaller JsonObjects.
     * 
     * @param Activity is a <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonObject.html">JsonObject</a>
     * that from which we will get the properties of the {@link Activity} object we are going to return.
     * @return the parsed {@link Activity} object. 
     */
    public Activity parseJsonObject(JsonObject Activity){
        String Id, Name, Description, RegionName, RegionId;

        JsonObject Detail = Activity.getAsJsonObject("Detail");
        JsonArray ODHTags = Activity.getAsJsonArray("ODHTags");
        JsonObject LocationInfo = Activity.getAsJsonObject("LocationInfo");

        String language = getLanguage(Detail);
        Detail = Detail.getAsJsonObject(language);
        
        Id = Activity.get("Id").getAsString();

        if(Id == null)
            return null;

        Name = Detail.get("Title").getAsString();

        Description = getDescription(Detail);
        
        String[] regionData = getRegionData(LocationInfo, language);  
        RegionId = regionData[0];
        RegionName = regionData[1];

        boolean hasGPSTrack = checkIfActivityHasGpsTracking(Activity);
        String[] Types = getTypes(ODHTags);

        logger.debug("Stored json object in Activity_"+Id);

        return new Activity(Id, Name, Description, RegionName, Types, hasGPSTrack, RegionId);
    }

    /**
     * The checkIfActivityHasGpsTracking methof takes a 
     * <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonObject.html">JsonObject</a>
     * to find out whether the activity has gps tracking or not.
     * A activity has gps tracking if any of these three properties "GpsPoints", "GpsTrack" and "GpsInfo" exists and is not empty or null.
     * But "GpsPoints", "GpsTrack" and "GpsInfo" which can be either a JsonObject, a JsonArray or a null. 
     * Since e don't know what these properties are we take them as <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonElement.html">JsonElements</a>,
     * and check if each of this properties is a JsonObject or a JsonArray, once we determine what that property is we check if it is empty
     * or not. If it is empty we check if the next property is empty or not and so on, if all of the properties are empty or null then we return false.
     * If even one of the properties is not empty then we return true. 
     * 
     * @param Activity is a <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonObject.html">JsonObject</a>
     * containing these three properties "GpsPoints", "GpsTrack" and "GpsInfo".
     * @return wether an activity has gps tracking or not
     */
    public boolean checkIfActivityHasGpsTracking(JsonObject Activity){
        String[] gpsInfo = {"GpsPoints", "GpsTrack", "GpsInfo"};

        for (String field : gpsInfo) {
            JsonElement element = Activity.get(field);

            if(element.isJsonObject()){
                JsonObject object = element.getAsJsonObject();

                if(object.toString().length() > 2){
                    logger.trace("GPSData found");
                    return true;
                }
                    
            }else if(element.isJsonArray()){
                JsonArray array = element.getAsJsonArray();
                if(array.size() > 0) {
                    logger.trace("GPSData found");
                    return true;
                }
            } 
        }
        logger.trace("No GPSData found");
        return false;

    }

    /**
     * The getLanguage method return the language in which
     * the data should be extracted. The preference is
     * english then italian and then german.
     * 
     * @param Detail is a  <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonObject.html">JsonObject</a>
     * containing various properties among which are the languages in which the data is available.
     * @return the  language in which the data should be extracted
     */
    public String getLanguage(JsonObject Detail){
        String language = "en";

        if(Detail.getAsJsonObject(language) == null)
            language = "it";
        
        if(Detail.getAsJsonObject(language) == null)
            language = "de";
        logger.trace("Language selected: "+language);
        return language;
    }
    
    /**
     * The getTypes method gets the types of activities available from a 
     * <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonArray.html">JsonArray</a>.
     * 
     * @param ODHTags is a <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonArray.html">JsonArray</a>
     * containing the types of activities available.
     * @return an array of strings containing the types of activities available.
     */
    public String[] getTypes(JsonArray ODHTags){
        String[] Types = new String[ODHTags.size()];

        for(int i = 0; i < ODHTags.size(); i++)
            Types[i] = ODHTags.get(i).getAsJsonObject().get("Id").getAsString();

        logger.trace("Found the following types: "+ Arrays.toString(Types));
        return Types;
    }


    /**
     * The getRegionData method gets the id and name of the region
     * from a <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonObject.html">JsonObject</a>
     * in the given language. This method return an array containing only 
     * null if the JsonObject is empty.
     * 
     * @param LocationInfo is a <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonObject.html">JsonObject</a>
     * containing information about the region in which the activiti is held.
     * @param language is the language in which the info should be retrived
     * @return a String array containing the id and name of the region
     */
    public String[] getRegionData(JsonObject LocationInfo, String language){
        String[] regionData = new String[2];
        JsonObject RegionInfo;

        regionData[0] = null; // first element is the Id of the region
        regionData[1] = null; // second element is the name of the region

        if(LocationInfo.get("RegionInfo").isJsonNull())
            return regionData;
        else
            RegionInfo = LocationInfo.getAsJsonObject("RegionInfo");

        regionData[0] = RegionInfo.get("Id").getAsString();
        regionData[1] = RegionInfo.getAsJsonObject("Name").
                                   get(language).getAsString();
        logger.trace(regionData);
        return regionData;
    }

    /**
     * The getDescription method will get the description
     * from a <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonObject.html">JsonObject</a>
     * remove the HTML-tags found in the description and then return it.
     * 
     * @param Detail is a <a href="https://www.javadoc.io/doc/com.google.code.gson/gson/2.6.2/com/google/gson/JsonObject.html">JsonObject</a> containing the description
     * @return the description
     */
    public String getDescription(JsonObject Detail){
        String Description;

        if(Detail.get("BaseText").isJsonNull()){
            logger.trace("Description is empty");
            return null;
        }else{
            Description = Detail.get("BaseText").
                          getAsString().
                          replaceAll("<.*?>", "");
        }
        logger.trace("Description found");
        return Description;
    }
    
}