package pp201920.project.a5;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.net.URL;

public class App{
    public static void main(String[] args)throws MalformedURLException{
        FileManager fileManager = new FileManager();
        String pathToInputFile = "src/main/resources/input.txt";
        int numOfObjects = fileManager.getNumOfObjects(pathToInputFile);

        URL url = new URL("https://tourism.opendatahub.bz.it/api/Activity?pagenumber=1&pagesize=" + numOfObjects);
        String results = MyRequest.fetchAndHandle(url);

        if(results != null){
            ArrayList<Activity> list = new ArrayList<>(numOfObjects);
            Analysis analyst = new Analysis();

            ActivityParser parser = new ActivityParser(list, results);
            parser.parseAndFillActivityList();

            for (Activity activity : list)
                analyst.performAnalysis(activity);

            String pathToResultsFolder = "results/";
            
            for(Activity activity : list){
                String fileName = "Activity_" + activity.getId();
                fileManager.generateJsonFile(activity, fileName, pathToResultsFolder, 0);
            }

            fileManager.generateJsonFile(
                fileManager.getAnalysisAsJsonObject(analyst),
                "analysis",
                pathToResultsFolder,
                1
            );
        }
    }  

}
