package pp201920.project.a5;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ActivityParser{

    final Logger logging = LogManager.getLogger();
    final MyLogger logger = new MyLogger(logging);
    
    ArrayList<Activity> list;
    JsonArray Items;

    public ActivityParser(){
        super();
    }

    public ActivityParser(ArrayList<Activity> list, String json){
        this.list = list;
        this.Items = getItems(json);
    }

    public void parseAndFillActivityList(){
        for (JsonElement item : this.Items) {

            Activity activity = parseJsonObject(
                item.getAsJsonObject()
            );

            if(activity != null)
                list.add(activity);
        }
    }

    public JsonArray getItems(String json){
        JsonObject jsonResponse = new JsonParser().
                                      parse(json).
                                      getAsJsonObject();

        return jsonResponse.get("Items").getAsJsonArray();
    }

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

    public String getLanguage(JsonObject Detail){
        String language = "en";

        if(Detail.getAsJsonObject(language) == null)
            language = "it";
        
        if(Detail.getAsJsonObject(language) == null)
            language = "de";
        logger.trace("Language selected: "+language);
        return language;
    }
    
    public String[] getTypes(JsonArray ODHTags){
        String[] Types = new String[ODHTags.size()];

        for(int i = 0; i < ODHTags.size(); i++)
            Types[i] = ODHTags.get(i).getAsJsonObject().get("Id").getAsString();

        logger.trace("Found the following types: "+ Arrays.toString(Types));
        return Types;
    }

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