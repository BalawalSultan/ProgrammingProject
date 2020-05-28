package pp201920.project.a5;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ActivityParser implements Runnable {
    
    ActivityMap map;
    JsonArray Items;

    public ActivityParser(ActivityMap map, String json){
        this.map = map;
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
                synchronized(this.map){
                    map.addActivity(activity);
                }
                generateJsonFile(activity);
            }
        }
    }

    public void generateJsonFile(Activity activity){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(activity);
        String path = "results/";

        try{
            FileWriter writer = new FileWriter(path + "Activity_" + activity.getId() + ".json");
            writer.write(json);
            writer.close();
            
            System.out.println("Successfully generated Activity_" + activity.getId() + ".json.");
        }catch(IOException e){
            System.out.println("An error occurred while generating Activity_" + activity.getId() + ".json.");
            e.printStackTrace();
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