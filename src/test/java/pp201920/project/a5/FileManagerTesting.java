package pp201920.project.a5;

public class FileManagerTesting {
    
	// //base url linking the actual url to the code for unit testing analysis
	
	// String baseUrl= "src/main/java/pp201920/project/a5";
	
    // public FileManager(){
    //     super();
    // }

    // public int getNumOfObjects(){
    	
    //     int result = 0;
    //     String path = "src/test/resources/FileManagerTest";
    //     File inputFile = new File(path + "input.txt");

    //     try{
    //         FileReader fileReader = new FileReader(inputFile);
    //         BufferedReader reader = new BufferedReader(fileReader);
    //         String s = reader.readLine();

    //         if(s != null)
    //             result = Integer.parseInt(s);

    //         reader.close();
    //         fileReader.close();

    //     }catch(NumberFormatException e){
    //         System.err.println("Input.txt does not contain a parsable integer!");
    //         e.printStackTrace();

    //     }catch(FileNotFoundException e){
    //         System.err.println("The file \"input.txt\" does not exist or is not in the specified path: " + path);
    //         e.printStackTrace();

    //     }catch(IOException e) {
    //         System.err.println("An error occurred while reading the file: input.txt");
    //         e.printStackTrace();
    //     }

    //     assertEquals(0, result);
    //     return result;
    // }

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
