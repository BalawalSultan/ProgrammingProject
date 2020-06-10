package pp201920.project.a5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.net.URL;

public class App{
    public static void main(String[] args)throws MalformedURLException{
        final MyLogger logger = new MyLogger(LogManager.getLogger());
        logger.info("Started the App!");
        String pathToInputFile = "input.txt";
        FileManager fileManager = new FileManager("src/main/resources/");
        int numOfObjects = fileManager.getNumOfObjects(pathToInputFile);
        logger.info(numOfObjects+" will be retrieved from OpenDataHub");

        URL url = new URL("https://tourism.opendatahub.bz.it/api/Activity?pagenumber=1&pagesize=" + numOfObjects);
        String results = MyRequest.fetchAndHandle(url);
        logger.info("Retrieved " + numOfObjects +" activities from OpenDataHUB");

        if(results != null){
            ArrayList<Activity> list = new ArrayList<>(numOfObjects);
            Analysis analyst = new Analysis();

            ActivityParser parser = new ActivityParser(list, results);
            parser.parseAndFillActivityList();                

            String pathToResultsFolder = "results/";
            
            for(Activity activity : list){
                analyst.performAnalysis(activity);
                String fileName = "Activity_" + activity.getId();
                fileManager.generateJsonFile(activity, fileName, pathToResultsFolder, 0);
            }

            logger.info("Converted data successfully from OpenDataHUB json to Activity.json format");

            String analysisFileName = "analysis";
            fileManager.generateJsonFile(
                fileManager.getAnalysisAsJsonObject(analyst),
                analysisFileName,
                pathToResultsFolder,
                1
            );
            logger.info("Analysis.json Generated successfully");
        }
        logger.info("End of Code");
    }  

}
