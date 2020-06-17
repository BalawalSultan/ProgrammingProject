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
* @version 1.0
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
     * @param pathToResources is the path to the resource folder
     */
    public FileManager(String pathToResources){
        this.pathToResources = pathToResources;
        this.validator = new JsonSchemaValidator(pathToResources);
    }

    /**
     * The getNumOfObjects calls the {@link #readNumOfObjects(String) readNumOfObjects} method
     * inside a try and catch in order to catch it's exception and returns 
     * the number of objects to be retrived.
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
     * @param fileName is the name of the generated file
     * @return the number of object to be retrived
     * @throws IllegalArgumentException is thrown when the input file has 0 as input
     * @throws IOException is thrown when there is an error reading the input file
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

    /**
     * The generateJsonFile method generates a json file of 
     * {@link Object} that is passed to it as parameter using the 
     * toJson(object) method of the {@link Gson} class.
     * 
     * @param object is the object to be serialized in a json file
     * @param fileName is the name of the generated json file
     * @param path is the path in which the file will be generated
     * @param schemaOption determines which json schema the generated json file should respect 
     */
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

    /**
     * The getAnalysisAsJsonObject generates a {@link JsonObject} to be used 
     * when creating the analysis.json file which will get it's content from
     * the analyst which is an object of the {@link Analysis} class. 
     * The toJson(object) method of the {@link Gson} class is used to get the properties
     * of the {@link Analysis} class as json where needed.
     * The variables containg the properties of the {@link Analysis} class as json
     * will be added as values of the properties of analysis.java output file.
     * 
     * 
     * @param analyst contains the results of the analysis performed on the various activities
     * @return returns a JsonObject containing the content of the analysis.json outputfile
     */
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