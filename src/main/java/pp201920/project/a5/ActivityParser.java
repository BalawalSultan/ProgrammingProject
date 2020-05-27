package pp201920.project.a5;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ActivityParser implements Runnable {
    
    ActivityList list;
    JsonArray Items;

    public ActivityParser(ActivityList list, String json){
        this.list = list;
        this.Items = getItems(json);
    }

    public JsonArray getItems(String json){
        JsonObject jsonResponse = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        return jsonResponse.get("Items").getAsJsonArray();
    }

    public void run(){
        for (JsonElement item : this.Items) {
            Activity activity = getActivityObject(item.getAsJsonObject());
        
            if(activity != null){
                synchronized(this.list){
                    list.addActivity(activity);
                }
            }
        }
    }

    public String getLanguage(JsonObject Detail){
        String language = "en";

        if(Detail.getAsJsonObject(language) == null)
            language = "it";
        
        if(Detail.getAsJsonObject(language) == null)
            language = "de";

        return language;
    }

    public Activity getActivityObject(JsonObject Activity){

        String Id, Name, Description, RegionName;

        JsonObject Detail = Activity.getAsJsonObject("Detail");
        JsonArray ODHTags = Activity.getAsJsonArray("ODHTags");
        JsonObject LocationInfo = Activity.getAsJsonObject("LocationInfo");
        JsonObject RegionInfo;

        if(LocationInfo.isJsonNull())
            RegionInfo = null;
        else{
            RegionInfo = LocationInfo.getAsJsonObject("RegionInfo");
            if(RegionInfo.isJsonNull())
                RegionInfo = null;
        }

        String language = getLanguage(Detail);
        Detail = Detail.getAsJsonObject(language);

        Id = Activity.get("Id").getAsString();

        Name = Detail.get("Title").getAsString();

        if(Detail.get("BaseText").isJsonNull()){
            Description = null;
        }else{
            Description = Detail.get("BaseText").getAsString();
        }
        
        if(RegionInfo.isJsonNull() || RegionInfo == null){
            RegionName = null;
        }else{
            RegionName = RegionInfo.getAsJsonObject("Name").
                                       get(language).getAsString();
        }

        boolean hasGPSTrack = true;

        JsonObject GpsPoints = Activity.getAsJsonObject("GpsPoints");
        JsonArray GpsTrack = Activity.getAsJsonArray("GpsTrack");
        JsonArray GpsInfo = Activity.getAsJsonArray("GpsInfo");

        if(GpsPoints.isJsonNull())
                hasGPSTrack = false;

        if(GpsTrack.isJsonNull())
            hasGPSTrack = false;
        
        if(GpsInfo.isJsonNull())
            hasGPSTrack = false;

        String[] Types = new String[ODHTags.size()];

        for(int i = 0; i < ODHTags.size(); i++)
            Types[i] = ODHTags.get(i).getAsJsonObject().get("Id").getAsString();

        return new Activity(Id, Name, Description, RegionName, Types, hasGPSTrack);
    }

}