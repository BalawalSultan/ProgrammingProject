package pp201920.project.a5;

import java.net.MalformedURLException;
import java.net.URL;

public class Runner {
    
    public static void main(String[] args)throws MalformedURLException{

        int numOfObjects = 500;
        URL url = new URL("https://tourism.opendatahub.bz.it/api/Activity?pagenumber=1&pagesize=" + numOfObjects);
        String results = MyRequest.fetchAndHandle(url);

        ActivityVector vector = new ActivityVector(numOfObjects);
        ActivityMap map = new ActivityMap();

        ActivityParser parser = new ActivityParser(map, vector, results);
        parser.parse();

        for (Activity activity : vector.getVector()){
            parser.generateJsonFile(activity);
        }

        System.out.println("The regions with " + map.getMostActivities() +
                           " activities are: " + map.getRegionsWithMostActivities());

        System.out.println("The regions with " + map.getLeastActivities() +
                           " activities are: " + map.getRegionsWithLeastActivities());

    }

}