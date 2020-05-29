package pp201920.project.a5;

import java.net.MalformedURLException;
import java.net.URL;

public class App{
    public static void main(String[] args)throws MalformedURLException{

        int numOfObjects = 500;
        URL url = new URL("https://tourism.opendatahub.bz.it/api/Activity?pagenumber=1&pagesize=" + numOfObjects);
        String results = MyRequest.fetchAndHandle(url);

        ActivityVector vector = new ActivityVector(numOfObjects);
        Analysis analyst = new Analysis();

        ActivityParser parser = new ActivityParser(analyst, vector, results);
        parser.parse();

        for (Activity activity : vector.getVector()){
            parser.generateJsonFile(activity);
        }

        analyst.showAnalysisResult();
    }  

}