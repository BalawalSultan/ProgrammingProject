package pp201920.project.a5;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ActivityParser{
    
    ArrayList<Activity> list;
    JsonArray Items;

    public ActivityParser(ArrayList<Activity> list, String json){
        this.list = list;
        this.Items = getItems(json);
    }

    public void parseAndFillActivityList(){
        for (JsonElement item : this.Items) {

            Activity activity = parseJsonObject(
                item.getAsJsonObject()
            );

            list.add(activity);
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

    public Activity parseJsonObject(JsonObject Activity){

        String Id, Name, Description, RegionName, RegionId;

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
            Description = Detail.get("BaseText").
                        getAsString().
                        .replaceAll("\\<[^>]*>", "").replaceAll("href=\"", "")
			            .replaceAll("<(\\\"[^\\\"]*\\\"|'[^']*'|[^'\\\">])*>","")
                        .replaceAll("<[a-zA-Z0-9]+>","") //Remove opening HTML-tags
                        .replaceAll("</[a-zA-Z0-9]+>",""); //Remove closing HTML-tags
        }
        
        if(RegionInfo == null){
            RegionName = null;
            RegionId = null;
        }else{
            RegionName = RegionInfo.getAsJsonObject("Name").
                                       get(language).getAsString();

            RegionId = RegionInfo.get("Id").getAsString();
        }

        String[] gpsInfo = {"GpsPoints", "GpsTrack", "GpsInfo"};
        boolean hasGPSTrack = false;

        for (String field : gpsInfo) {
            JsonElement element = Activity.get(field);

            if(element.isJsonObject()){
                JsonObject object = element.getAsJsonObject();

                if(object.toString().length() > 2)
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

        return new Activity(Id, Name, Description, RegionName, Types, hasGPSTrack, RegionId);
    }

}