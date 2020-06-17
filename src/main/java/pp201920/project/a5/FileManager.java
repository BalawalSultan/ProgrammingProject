package pp201920.project.a5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;

/**
*
* The FileManager class contains the methods necessary to 
* generate the activity_XXX.json and analysis.json output files.
*
* @author  S. Balawal
* @author  A. Nicoletti
* @author R.Zorzi
*
*/

public class FileManager {
    
    final Logger logging = LogManager.getLogger();
    final MyLogger logger = new MyLogger(logging);
    JsonSchemaValidator validator;
    String pathToResources;

    /**
     * Constructor for FileManager
     * Takes as input parameter the path to the resource folder
     * and initializes the validator, which is an object of the {@link JsonSchemaValidator} class,
     * passing the pathToResources variable to it's constructor.
     * 
     * @param pathToResources
     */
    public FileManager(String pathToResources){
        this.pathToResources = pathToResources;
        this.validator = new JsonSchemaValidator(pathToResources);
    }

    /**
     * The getNumOfObjects calls the {@link readNumOfObjects(string) readNumOfObjects} method
     * inside a try and catch in order to catch it's exception
     * and returns the number of objects to be retrived.
     * 
     * @param fileName the name of the file containing the number of objects to be retrived
     * @return the number of objects to be retrived
     */
    public int getNumOfObjects(String fileName){
        int result = 0;
        try {
            result = readNumOfObjects(fileName);
            
        } catch(NumberFormatException e){
            logger.error("Input.txt does not contain a parsable integer!");
            logger.error(e);

        } catch(IllegalArgumentException e){
            logger.error("Input can't be empty or 0!");
            logger.error(e);

        } catch(FileNotFoundException e){
            logger.error("The file \"input.txt\" does not exist or is not in the resource folder");
            logger.error(e);

        }catch(IOException e) {
            logger.error("An error occurred while reading the file: input.txt");
            logger.error(e);
        }
        return result;
    }

    /**
     * The readNumOfObjects method reads the number of objects to be retrived.
     * For testing purposes this method throws exceptions without trying to 
     * catch them, this way when we test this method we can see if the method
     * throws exception when it should.
     * 
     * @param path 
     * @return the number of object to be retrived
     * @throws IllegalArgumentException
     * @throws IOException
     */
    public int readNumOfObjects(String fileName) throws IllegalArgumentException, IOException {
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

        //checks if the json respects it's json schema
        if(validator.validateSchema(json, schemaOption)){
            try(FileWriter fileWriter = new FileWriter(path + fileName + ".json")){

                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);  
                bufferedWriter.write(json);
                bufferedWriter.close();

            }catch(IOException e){
                logger.error("An error occurred while generating " + fileName + ".json.");
                logger.error(e);
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