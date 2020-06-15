package pp201920.project.a5;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonSchemaValidatorTest {
    
    final JsonSchemaValidator validator = new JsonSchemaValidator("src/test/resources/");

    public String getActivityAsJson(String id, String name, String description, String types, String region){
      return "{"+
          "\"id\": \"" + id + "\"," + 
          "\"name\": \"" + name + "\"," + 
          "\"description\": \"" + description + "\"," + 
          "\"types\": " + types +"," + 
          "\"hasGPSTrack\": true," + 
          "\"region\": \"" + region + "\"" +
      "}";
    }

    public String getAnalysisAsJson(String activitiesTypes, String trackedActivityIds, String numberOfActivities, String regionIds){
      return "{" + 
        "\"activitiesTypes\": {" + activitiesTypes + "}," +
        "\"trackedActivityIds\": [" + trackedActivityIds + "]," +
        "\"regionsWithMostActivities\": {" +
          "\"numberOfActivities\": " + numberOfActivities +"," +
          "\"regionIds\": [ "+ regionIds +"]" +
        "}," +
        "\"regionsWithMostActivities\": {" +
          "\"numberOfActivities\": " + numberOfActivities +"," +
          "\"regionIds\": [ "+ regionIds +"]" +
        "}" +
      "}";
    }

    @Test
    public void validateSchemaTest(){
        String jsonActivity = getActivityAsJson(
            "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA", 
            "Name", "null", "null", "Random region"
        );

        boolean result = validator.validateSchema(jsonActivity, 0);
        assertEquals(false, result);
    }

}