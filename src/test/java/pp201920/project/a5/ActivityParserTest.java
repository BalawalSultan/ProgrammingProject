package pp201920.project.a5;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class ActivityParserTest {

    private String getDescriptionAsJson(String baseText){
        return  "{"+
                baseText +
        "}";
    }        

    private String getActivityAsJson(String GpsPointsProperty, String GpsInfo, String GpsTrack){
        return "{"+ 
                "'GpsPoints':" + GpsPointsProperty + ","+
                "'GpsInfo':" + GpsInfo + "," +
                "'GpsTrack':" + GpsTrack +
        "}";
    }

    private String getLocationInfoAsJson(String Id, String Name){
        if(Id == null && Name == null)
                return "{'RegionInfo': null}";

        return "{"+
                "'RegionInfo':{" +
                        Id + "," +
                        Name +
                "}"+
        "}";
    }

    @Test
    public void getDescriptionTest(){
        String json = getDescriptionAsJson("'BaseText': 'AAA'");

        JsonObject Detail = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        String description = parser.getDescription(Detail);
        boolean result = description.equals("AAA");

        assertEquals(true, result, "The description should be AAA.");
    }

    @Test
    public void getDescriptionNullTest(){
        String json = getDescriptionAsJson("'BaseText': null");

        JsonObject Detail = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        String description = parser.getDescription(Detail);
        boolean result = (description == null);

        assertEquals(true, result, "The description should be null.");
    }

    @Test
    public void getDescriptionEmptyTest(){
        String json = getDescriptionAsJson("'BaseText': ''");

        JsonObject Detail = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        String description = parser.getDescription(Detail);
        boolean result = description.isEmpty();
        
        assertEquals(true, result, "The description should be empty.");
    }

    @Test
    public void getRegionDataIdTest(){
        String json = getLocationInfoAsJson("'Id': '123'", "'Name':{'it': 'Trentino-Alto Adige'}");

        JsonObject LocationInfo = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        String[] regionData = parser.getRegionData(LocationInfo, "it");
        boolean result = regionData[0].equals("123");

        assertEquals(true, result, "The Id should be 123.");
    }

    @Test
    public void getRegionDataNameTest(){
        String json = getLocationInfoAsJson("'Id': '123'", "'Name':{'it': 'Trentino-Alto Adige'}");

        JsonObject LocationInfo = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        String[] regionData = parser.getRegionData(LocationInfo, "it");
        boolean result = regionData[1].equals("Trentino-Alto Adige");
        
        assertEquals(true, result, "The name should be Trentino-Alto Adige.");
    }

    @Test
    public void getRegionDataNullTest(){
        String json = getLocationInfoAsJson(null, null);

        JsonObject LocationInfo = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        String[] regionData = parser.getRegionData(LocationInfo, "it");
        boolean result = (regionData[0] == null && regionData[1] == null);
        
        assertEquals(true, result, "regionData should only contain null values.");
    }

    @Test
    public void checkIfActivityHasGpsTrackingTest(){
        String json = getActivityAsJson("{'property': 'property value'}","[{}]","[{},{}]");

        JsonObject Activity = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        boolean result = parser.checkIfActivityHasGpsTracking(Activity);

        assertEquals(true, result, "This activity should have gps tracking.");
    }

    @Test
    public void checkIfActivityHasGpsTrackingIsFalseTest(){
        String json = getActivityAsJson("null","[]","[]");

        JsonObject Activity = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        boolean result = parser.checkIfActivityHasGpsTracking(Activity);

        assertEquals(false, result, "This activity should not have gps tracking.");
    }

    @Test
    public void checkIfActivityHasGpsTrackingNoGpsPointsTest(){
        String json = getActivityAsJson("null","[{}]","[]");

        JsonObject Activity = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        boolean result = parser.checkIfActivityHasGpsTracking(Activity);

        assertEquals(true, result, "This activity should have gps tracking.");
    }

    @Test
    public void checkIfActivityHasGpsTrackingNoGpsInfoTest(){
        String json = getActivityAsJson("{'property': 'property value'}","[]","[{},{}]");

        JsonObject Activity = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        boolean result = parser.checkIfActivityHasGpsTracking(Activity);

        assertEquals(true, result, "This activity should have gps tracking.");
    }

    @Test
    public void checkIfActivityHasGpsTrackingNoGpsTrackTest(){
        String json = getActivityAsJson("{'property': 'property value'}","[{}]","[]");

        JsonObject Activity = new JsonParser().
                                parse(json).
                                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        boolean result = parser.checkIfActivityHasGpsTracking(Activity);

        assertEquals(true, result, "This activity should have gps tracking.");
    }

}