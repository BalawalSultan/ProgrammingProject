package pp201920.project.a5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class FileManager {
    
    public int getNumOfObjects(){
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

        }catch(FileNotFoundException e){
            System.err.println("The file \"input.txt\" does not exist or is not in the specified path: " + path);
            e.printStackTrace();

        }catch(IOException e) {
            System.err.println("An error occurred while reading the file: input.txt");
            e.printStackTrace();
        }

        return result;
    }

    public void generateJsonFile(Object object, String fileName){
        Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().create();
        String json = gson.toJson(object);
        String path = "results/";

        try{
            FileWriter writer = new FileWriter(path + fileName + ".json");
            writer.write(json);
            writer.close();
        }catch(IOException e){
            System.out.println("An error occurred while generating " + fileName + ".json.");
            e.printStackTrace();
        }
    }

    public JsonObject getAnalysisAsJsonObject(Analysis analyst){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String activitiesTypes = gson.toJson(analyst.getActivitiesTypes());
        String trackedActivityIds = gson.toJson(analyst.getTrackedActivityIds());
        String mostActivityRegions = gson.toJson(analyst.getRegionsWithMostActivities());
        String leastActivityRegions = gson.toJson(analyst.getRegionsWithLeastActivities());

        JsonObject regionsWithMostActivities = new JsonObject();
        regionsWithMostActivities.addProperty("numberOfActivities", analyst.getMostActivities());
        regionsWithMostActivities.addProperty("regionIds", mostActivityRegions);

        JsonObject regionsWithLeastActivities = new JsonObject();
        regionsWithLeastActivities.addProperty("numberOfActivities", analyst.getLeastActivities());
        regionsWithLeastActivities.addProperty("regionIds", leastActivityRegions);

        String json = "{" + 
                        "activitiesTypes:" + activitiesTypes + "," +
                        "trackedActivityIds:" + trackedActivityIds + "," + 
                        "regionsWithMostActivities:" + regionsWithMostActivities + "," +
                        "regionsWithLeastActivities:" + regionsWithLeastActivities + 
                    "}";

        JsonObject analysis = new JsonParser().parse(json).getAsJsonObject();

        return analysis;
    }

}