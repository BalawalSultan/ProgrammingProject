package pp201920.project.a5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class App{
    public static void main(String[] args){
        int numOfObjects =  getObjectsToRetrieve(); // Number of Objects to retrieve
        String url = "https://tourism.opendatahub.bz.it/api/Activity?pagenumber=1&pagesize=" + numOfObjects;
        String json = fetchAndHandle(url);

        if(json != null && numOfObjects != 0){
            JsonObject jsonResponse = new JsonParser().parse(json).getAsJsonObject();
            JsonArray Objects = jsonResponse.get("Items").getAsJsonArray();

            ArrayList<Activity> list = new ArrayList<>();

            for (JsonElement object : Objects) {
                Activity activity = getActivityObject(object.getAsJsonObject());
                list.add(activity);
                generateJsonFile(activity);
            }
            
            regionWithMostActivities(list);
        }

    }

    public static int getObjectsToRetrieve(){
        int result = 0;
        String path = "src/main/resources/";
        File inputFile = new File(path + "input.txt");

        try{
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader reader = new BufferedReader(fileReader);

            String s = reader.readLine();

            if(s != null)
                result = Integer.parseInt(s);
            reader.close();
            fileReader.close();

        }catch(NumberFormatException e){
            System.err.println("Input.txt does not contain a parsable integer!");
            e.printStackTrace();

        } catch(FileNotFoundException e){
            System.err.println("The file \"input.txt\" does not exist or is not in the specified path: " + path);
            e.printStackTrace();

        } catch(IOException e) {
                System.err.println("An error occurred while reading the file: input.txt");
                e.printStackTrace();
        }

        return result;
    }

    public static String getLanguage(JsonObject Detail, JsonObject RegionName){
        String language = "en";

        if(Detail.getAsJsonObject(language) == null || RegionName.get(language) == null)
            if(Detail.getAsJsonObject("it") != null || RegionName.get("it") != null)
                language = "it";
            else
                language = "de";

        return language;
    }

    public static Activity getActivityObject(JsonObject Activity){

        JsonObject Detail = Activity.getAsJsonObject("Detail");
        JsonArray ODHTags = Activity.getAsJsonArray("ODHTags");
        JsonObject RegionInfo = Activity.getAsJsonObject("LocationInfo").
                                        getAsJsonObject("RegionInfo");

        String language = getLanguage(Detail, RegionInfo.getAsJsonObject("Name"));

        String Id = Activity.get("Id").getAsString();

        String Name = Detail.getAsJsonObject(language).
                             get("Title").getAsString();

        String Description = Detail.getAsJsonObject(language).
                                    get("BaseText").getAsString();
                                    
         Description = Description.replaceAll("<[a-zA-Z0-9]+>","").replaceAll("</[a-zA-Z0-9]+>","");

        String RegionName = RegionInfo.getAsJsonObject("Name").
                                       get(language).getAsString();

        boolean hasGPSTrack = false;

        JsonObject GpsPoints = Activity.getAsJsonObject("GpsPoints");
        JsonArray GpsTrack = Activity.getAsJsonArray("GpsTrack");
        JsonArray GpsInfo = Activity.getAsJsonArray("GpsInfo");

        if(GpsInfo != null || GpsTrack != null || GpsPoints != null)
            hasGPSTrack = true;

        String[] Types = new String[ODHTags.size()];

        for(int i = 0; i < ODHTags.size(); i++)
            Types[i] = ODHTags.get(i).getAsJsonObject().get("Id").getAsString();

        return new Activity(Id, Name, Description, RegionName, Types, hasGPSTrack);

    }

    public static void generateJsonFile(Activity activity){
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

    public static void regionWithMostActivities(ArrayList<Activity> list){
        HashMap<String, Integer> map = new HashMap<>();

        for (Activity activity : list) {
            String region = activity.getRegion();
            int activity_number = activity.getTypes().length;

            if(!map.containsKey(region))
                    map.put(region,activity_number);
            else{
                map.put(region, map.get(region) + activity_number);
            }
        } 

        ArrayList<String> max_regions = new ArrayList<>();
        ArrayList<String> min_regions = new ArrayList<>();

        int max = Collections.max(map.values());
        int min = Collections.min(map.values());   

        map.forEach((key, value) -> {
            if (value == max)
                max_regions.add(key);

            if (value == min)
                min_regions.add(key);
        });

        System.out.println("\nThe region(s) that have most activities are " + max_regions);
        System.out.println("The region(s) that have the least activities are " + min_regions);

    }

    public static String fetchAndHandle(String url){
        String response = null;
        
        try{
            response = fetch(new URL(url));

        }catch(MalformedURLException e){
            System.err.println("MalformedURLException!");
            e.printStackTrace();

        }catch(NullPointerException e){
            System.err.println("Empty Response!");
            e.printStackTrace();

        }catch(FileNotFoundException e){
            System.err.println("Error 404: FileNotFoundException!");
            e.printStackTrace();

        }catch(IOException e){
            System.err.println("IOException!");
            e.printStackTrace();
        }

        return response;
    }

    public static String fetch(URL url) throws IOException{        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();

        StringBuilder response = new StringBuilder();
        BufferedReader reader;
        String line;

        if (status == 404)
            throw new FileNotFoundException();
        if (status > 299)
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        else
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        while ((line = reader.readLine()) != null)
            response.append(line);
            
        reader.close();

        if(response.toString().isEmpty())
            throw new NullPointerException();

        return response.toString();
    }
}
