package pp201920.project.a5;

import java.io.*;
import com.google.gson.*;

public class FileManager {
    
    JsonSchemaValidator validator;
    String pathToResources;

    public FileManager(String pathToResources){
        this.pathToResources = pathToResources;
        this.validator = new JsonSchemaValidator(pathToResources);
    }

    public int getNumOfObjects(String fileName){
        int result = 0;
        try {
            result = readNumOfObjects(pathToResources + fileName);
            
        } catch(NumberFormatException e){
            System.err.println("Input.txt does not contain a parsable integer!");
            e.printStackTrace();

        } catch(IllegalArgumentException e){
            System.err.println("Input can't be empty or 0!");
            e.printStackTrace();

        } catch(FileNotFoundException e){
            System.err.println("The file \"input.txt\" does not exist or is not in the resource folder");
            e.printStackTrace();

        }catch(IOException e) {
            System.err.println("An error occurred while reading the file: input.txt");
            e.printStackTrace();
        }
        return result;
    }

    public int readNumOfObjects(String fileName) throws NumberFormatException,IllegalArgumentException,FileNotFoundException,IOException {
        int result = 0;
        File inputFile = null;

        if(pathToResources != null)
             inputFile = new File(pathToResources + fileName);

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

    public void generateJsonFile(Object object, String fileName, String path, int schemaOption){
        Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().setPrettyPrinting().create();
        String json = gson.toJson(object);
        File folder = new File(path);

        if(!folder.isDirectory())
            folder.mkdirs();

        //Replaces "&" with it's unicode so that in the json file you see & instead of \u0026
        json.replaceAll("&", "\\u0026");

        if(validator.validateSchema(json, schemaOption)){
            try(FileWriter fileWriter = new FileWriter(path + fileName + ".json")){

                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);  
                bufferedWriter.write(json);
                bufferedWriter.close();

            }catch(IOException e){
                System.out.println("An error occurred while generating " + fileName + ".json.");
                e.printStackTrace();
            }
            
        }else{
            System.out.println(fileName + " does not respect the json schema.");
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
                   parse(json).
                   getAsJsonObject();
    }

}