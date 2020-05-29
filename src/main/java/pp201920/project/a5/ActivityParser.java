package pp201920.project.a5;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ActivityParser{
    
    ActivityVector vector;
    JsonArray Items;

    public ActivityParser(ActivityVector vector, String json){
        this.vector = vector;
        this.Items = getItems(json);
    }

    public void parse(){
        for (JsonElement item : this.Items) {
            Activity activity = getActivityObject(item.getAsJsonObject());
            vector.addActivity(activity);
        }
    }

    public JsonArray getItems(String json){
        JsonObject jsonResponse = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        return jsonResponse.get("Items").getAsJsonArray();
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
            if(LocationInfo.get("RegionInfo").isJsonNull() == true)
                RegionInfo = null;
            else
                RegionInfo = LocationInfo.getAsJsonObject("RegionInfo");
        }

        String language = getLanguage(Detail);
        Detail = Detail.getAsJsonObject(language);

        Id = Activity.get("Id").getAsString();
        Name = Detail.get("Title").getAsString();

        if(Detail.get("BaseText").isJsonNull()){
            Description = null;
        }else{
            Description = Detail.get("BaseText").getAsString();
            
            Description = Description.replaceAll("<[a-zA-Z0-9]+>","").
                          replaceAll("</[a-zA-Z0-9]+>","");
        }
        
        if(RegionInfo == null){
            RegionName = null;
        }else{
            RegionName = RegionInfo.getAsJsonObject("Name").
                                       get(language).getAsString();
        }

        boolean hasGPSTrack = false;

        String[] gpsInfo = {"GpsPoints", "GpsTrack", "GpsInfo"};

        for (String field : gpsInfo) {
            JsonElement e = Activity.get(field);
            if(e.isJsonNull() == false)
                hasGPSTrack = true;
        }

        String[] Types = new String[ODHTags.size()];

        for(int i = 0; i < ODHTags.size(); i++)
            Types[i] = ODHTags.get(i).getAsJsonObject().get("Id").getAsString();

        return new Activity(Id, Name, Description, RegionName, Types, hasGPSTrack);
    }

}