package pp201920.project.a5;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ActivityParser{
    
    ActivityList list;
    JsonArray Items;

    public ActivityParser(ActivityList list, String json){
        this.list = list;
        this.Items = getItems(json);
    }

    public void parse(){
        for (JsonElement item : this.Items) {
            Activity activity = getActivityObject(item.getAsJsonObject());
            list.addActivity(activity);
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

        if(LocationInfo.get("RegionInfo").isJsonNull())
            RegionInfo = null;
        else
            RegionInfo = LocationInfo.getAsJsonObject("RegionInfo");

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

        String[] gpsInfo = {"GpsPoints", "GpsTrack", "GpsInfo"};
        boolean hasGPSTrack = false;

        for (String field : gpsInfo) {
            JsonElement element = Activity.get(field);

            if(element.isJsonObject()){
                JsonObject object = element.getAsJsonObject();

                if(object.toString().length() == 2)
                    hasGPSTrack = false;
                else
                    hasGPSTrack = true;
                    
            }else if(element.isJsonArray()){
                JsonArray array = element.getAsJsonArray();
                    if(array.size() > 0)
                        hasGPSTrack = true;
                        
            }    
        }

        String[] Types = new String[ODHTags.size()];

        for(int i = 0; i < ODHTags.size(); i++)
            Types[i] = ODHTags.get(i).getAsJsonObject().get("Id").getAsString();

        return new Activity(Id, Name, Description, RegionName, Types, hasGPSTrack);
    }

}