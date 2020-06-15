package pp201920.project.a5;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JsonSchemaValidatorTest {
    
    final JsonSchemaValidator validator = new JsonSchemaValidator("src/test/resources/");

    public String getActivityAsJson(String id, String name, String description, String types, String region){
      return "{"+
          "\"id\": " + id + "," + 
          "\"name\": " + name + "," + 
          "\"description\": " + description + "," + 
          "\"types\": " + types +"," + 
          "\"hasGPSTrack\": true," + 
          "\"region\": " + region + "" +
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
    public void iDTest(){
      String jsonActivity = getActivityAsJson(
        "\"A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2\"", 
        "\"aaaaaa\"", "\"description\"", "[\"ss\"]", "\"Random region\""
      );

      boolean result = validator.validateSchema(jsonActivity, 0);
      assertEquals(true, result, "The length of the ID should be 32 charcters.");
    }

    @Test
    public void iDLengthTest(){
      String jsonActivity = getActivityAsJson(
        "\"A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2\"", 
        "\"aaaaaa\"", "\"description\"", "[\"ss\"]", "\"Random region\""
      );

      boolean result = validator.validateSchema(jsonActivity, 0);
      assertEquals(false, result, "The length of the ID should be 32 charcters.");
    }

    @Test
    public void iDNumberTest(){
      String jsonActivity = getActivityAsJson(
        "\"11111111111111111111111111111111\"", 
        "\"aaaaaa\"", "\"description\"", "[\"ss\"]", "\"Random region\""
      );

      boolean result = validator.validateSchema(jsonActivity, 0);
      assertEquals(false, result, "The ID cannot be made out of numbers only.");
    }

    @Test
    public void iDCharacterTest(){
      String jsonActivity = getActivityAsJson(
        "\"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\"", 
        "\"Name\"", "\"description\"", "[\"ss\"]", "\"Random region\""
      );

      boolean result = validator.validateSchema(jsonActivity, 0);
      assertEquals(false, result, "The ID cannot be made out of characters only.");
    }

    @Test
    public void iDSpecialCharTest(){
      String jsonActivity = getActivityAsJson(
        "\"A2@2!£A2A2A2A%A&A$A2?2^2*0-A2/2A\"", 
        "\"Name\"", "\"description\"", "[\"ss\"]", "\"Random region\""
      );
      
      boolean result = validator.validateSchema(jsonActivity, 0);
      assertEquals(false, result, "The length of the ID must contain no special characters.");
    }

    @Test
    public void iDNullTest(){
      String jsonActivity = getActivityAsJson(
        "null", "\"Name\"", "\"description\"", "[\"ss\"]", "\"Random region\""
      );
      
      boolean result = validator.validateSchema(jsonActivity, 0);
      assertEquals(false, result, "The ID cannot be null.");
    }

    @Test
    public void nameNullTest(){
      String jsonActivity = getActivityAsJson(
        "\"A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2A\"",
        "null", "\"description\"", "[\"ss\"]", "\"Random region\""
      );
      
      boolean result = validator.validateSchema(jsonActivity, 0);
      assertEquals(false, result, "The name cannot be null.");
    }

    @Test
    public void nameTest(){
      String jsonActivity = getActivityAsJson(
        "\"A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2\"",
        "\"4-wins-13\"", "\"description\"", "[\"ss\"]", "\"Random region\""
      );
      
      boolean result = validator.validateSchema(jsonActivity, 0);
      assertEquals(true, result, "The name 4-wins-13 should be valid.");
    }

    @Test
    public void descriptionTest(){
      String jsonActivity = getActivityAsJson(
        "\"A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2\"",
        "\"4-wins-13\"", "\"description\"", "[\"ss\"]", "\"Random region\""
      );
      
      boolean result = validator.validateSchema(jsonActivity, 0);
      assertEquals(true, result, "The description \"description\" should be valid.");
    }

    @Test
    public void descriptionNullTest(){
      String jsonActivity = getActivityAsJson(
        "\"A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2A2\"",
        "\"4-wins-13\"", "null", "[\"ss\"]", "\"Random region\""
      );
      
      boolean result = validator.validateSchema(jsonActivity, 0);
      assertEquals(true, result, "The description can be null.");
    }

}