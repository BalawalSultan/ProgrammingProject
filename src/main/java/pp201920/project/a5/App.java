package pp201920.project.a5;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.net.URL;

public class App{
    public static void main(String[] args)throws MalformedURLException{
        String pathToInputFile = "input.txt";
        FileManager fileManager = new FileManager("src/main/resources/");
        int numOfObjects = fileManager.getNumOfObjects(pathToInputFile);

        URL url = new URL("https://tourism.opendatahub.bz.it/api/Activity?pagenumber=1&pagesize=" + numOfObjects);
        String results = MyRequest.fetchAndHandle(url);

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

            String analysisFileName = "analysis";
            fileManager.generateJsonFile(
                fileManager.getAnalysisAsJsonObject(analyst),
                analysisFileName,
                pathToResultsFolder,
                1
            );
        }
    }  

}
