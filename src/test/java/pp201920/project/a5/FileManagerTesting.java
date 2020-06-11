package pp201920.project.a5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import org.junit.jupiter.api.Test;

public class FileManagerTesting {
    
	// //base url linking the actual url to the code for unit testing analysis
	// String baseUrl= "src/main/java/pp201920/project/a5";

    // public void generateJsonFile(Object object, String fileName){
    //     Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().create();
    //     String json = gson.toJson(object);
    //     String path = "results/";

    //     try(FileWriter fileWriter = new FileWriter(path + fileName + ".json")){
    //         BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);            
    //         bufferedWriter.write(json);
    //         bufferedWriter.close();
    //     }catch(IOException e){
    //         System.out.println("An error occurred while generating " + fileName + ".json.");
    //         e.printStackTrace();
    //     }
    // }
    
    // @Test
    // public JsonObject getAnalysisAsJsonObject(Analysis analyst){
    //     Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //     String activitiesTypes = gson.toJson(analyst.getActivitiesTypes());
    //     assertEquals(, activities);
        
    //     String trackedActivityIds = gson.toJson(analyst.getTrackedActivityIds());
    //     assertEquals(, trackedActivityIds);
        
    //     String regionsWithMostActivitiesId = gson.toJson(analyst.getRegionsWithMostActivities());
    //     assertEquals(, regionsWihMostActivitiesId);
        
    //     String regionsWithLeastActivitiesId = gson.toJson(analyst.getRegionsWithLeastActivities());
    //     assertEquals(, regionsWithLeastActivitiesId);

    //     String json ="{" + 
    //                     "activitiesTypes:" + activitiesTypes + "," +
    //                     "trackedActivityIds:" + trackedActivityIds + "," + 
    //                     "regionsWithMostActivities:{" +
    //                         "numberOfActivities:" + analyst.getMostActivities() + "," +
    //                         "regionIds:" + regionsWithMostActivitiesId +
    //                     "}," +
    //                     "regionsWithLeastActivities:{" +
    //                         "numberOfActivities:" + analyst.getLeastActivities() + "," +
    //                         "regionIds:" + regionsWithLeastActivitiesId +
    //                     "}" +
    //                 "}";

    //     return new JsonParser().
    //                         parse(json).getAsJsonObject();
    // }

}
