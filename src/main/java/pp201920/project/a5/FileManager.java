package pp201920.project.a5;

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
import com.google.gson.JsonParser;

public class FileManager {
    
    public FileManager(){
        super();
    }

    public int getNumOfObjects(String path){
        int result = 0;
        try {
            result = readNumOfObjects(path);
        } catch(NumberFormatException e){
            System.err.println("Input.txt does not contain a parsable integer!");
            e.printStackTrace();

        } catch(IllegalArgumentException e){
            System.err.println("Input can't be empty or 0!");
            e.printStackTrace();

        } catch(FileNotFoundException e){
            System.err.println("The file \"input.txt\" does not exist or is not in the specified path: " + path);
            e.printStackTrace();

        }catch(IOException e) {
            System.err.println("An error occurred while reading the file: input.txt");
            e.printStackTrace();
        }
        return result;
    }

    public int readNumOfObjects(String path) throws NumberFormatException,IllegalArgumentException,FileNotFoundException,IOException {
        int result = 0;

        File inputFile = null;

        if(path != null)
             inputFile = new File(path);
        assert inputFile != null;
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader reader = new BufferedReader(fileReader);
        String s = reader.readLine();

        if (s != null)
            result = Integer.parseInt(s);

        reader.close();
        fileReader.close();

        //if the file is empty
        if (Integer.parseInt(String.valueOf(result)) == 0)
            throw new IllegalArgumentException();
        return result;
    }

    public void generateJsonFile(Object object, String fileName, String path){
        Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().create();
        String json = gson.toJson(object);
        File folder = new File(path);

        if(!folder.isDirectory())
            folder.mkdirs();

        //Replaces "&" with it's unicode so that in the json file you see & instead of \u0026
        json.replaceAll("&", "\\u0026");

        try(FileWriter fileWriter = new FileWriter(path + fileName + ".json")){
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);            
            bufferedWriter.write(json);
            bufferedWriter.close();
        }catch(IOException e){
            System.out.println("An error occurred while generating " + fileName + ".json.");
            e.printStackTrace();
        }
    }

    public JsonObject getAnalysisAsJsonObject(Analysis analyst){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        String activitiesTypes = gson.toJson(analyst.getActivitiesTypes());
        String trackedActivityIds = gson.toJson(analyst.getTrackedActivityIds());
        String regionsWithMostActivitiesId = gson.toJson(analyst.getRegionsWithMostActivities());
        String regionsWithLeastActivitiesId = gson.toJson(analyst.getRegionsWithLeastActivities());

        String json ="{" + 
                        "activitiesTypes:" + activitiesTypes + "," +
                        "trackedActivityIds:" + trackedActivityIds + "," + 
                        "regionsWithMostActivities:{" +
                            "numberOfActivities:" + analyst.getMostActivities() + "," +
                            "regionIds:" + regionsWithMostActivitiesId +
                        "}," +
                        "regionsWithLeastActivities:{" +
                            "numberOfActivities:" + analyst.getLeastActivities() + "," +
                            "regionIds:" + regionsWithLeastActivitiesId +
                        "}" +
                    "}";

        return new JsonParser().
                            parse(json).getAsJsonObject();
    }

}