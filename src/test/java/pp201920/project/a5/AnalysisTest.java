package pp201920.project.a5;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AnalysisTest{
    
    @Test
    public void hasGPSTrackAnalysisTest(){
        Analysis analyst = new Analysis();
        analyst.hasGPSTrackAnalysis(true, "123");

        boolean result = analyst.
                         getTrackedActivityIds().
                         contains("123");

        assertEquals(true, result, "The activity with Id = 123 should be among the tracked activities.");
    }

    @Test
    public void hasNoGPSTrackAnalysisTest(){
        Analysis analyst = new Analysis();
        analyst.hasGPSTrackAnalysis(false, "123");

        boolean result = analyst.
                         getTrackedActivityIds().
                         contains("123");

        assertEquals(false, result, "The activity with Id = 123 should not be among the tracked activities.");
    }

    @Test
    public void activityTypesAnalysisTest(){
        Analysis analyst = new Analysis();
        String[] types = {
            "type_a",
            "type_b"
        };

        analyst.activityTypesAnalysis(types);
        
        boolean result = analyst.
                         getActivitiesTypes().
                         containsKey("type_a");

        assertEquals(true, result, "The HashMap<String, Integer> activityTypes should contain the type_a.");
    }

    @Test
    public void activityTypesAnalysisTypeValueTest(){
        Analysis analyst = new Analysis();
        String[] types = {
            "type_a",
            "type_a"
        };

        analyst.activityTypesAnalysis(types);
        
        int  result = 0;
        if(analyst.getActivitiesTypes().containsKey("type_a"))
            result = analyst.getActivitiesTypes().get("type_a");

        assertEquals(2, result, "The HashMap<String, Integer> activityTypes should contain the type_a and its value should be 2.");
    }

    @Test
    public void getRegionActivitiesTest(){
        Analysis analyst = new Analysis();
        analyst.regionAnalysis("Region_1_ID");
        
        boolean result = analyst.
                         getRegionActivities().
                         containsKey("Region_1_ID");

        assertEquals(true, result, "The HashMap<String, Integer> RegionActivities should contain the id of Region_1.");
    }

    @Test
    public void getMostActivitiesTest(){
        Analysis analyst = new Analysis();
        analyst.regionAnalysis("Region_1_ID");
        analyst.regionAnalysis("Region_1_ID");
        analyst.regionAnalysis("Region_2_ID");

        int result = analyst.getMostActivities();
        
        assertEquals(2, result, "The regions with the most activities should have 2 activities.");
    }

    @Test
    public void getLeastActivitiesTest(){
        Analysis analyst = new Analysis();
        analyst.regionAnalysis("Region_1_ID");
        analyst.regionAnalysis("Region_1_ID");
        analyst.regionAnalysis("Region_2_ID");

        int result = analyst.getLeastActivities();

        assertEquals(1, result, "The regions with the least activities should have 1 activity.");
    }

    @Test
    public void getRegionWithLeastActivitiesTest(){
        Analysis analyst = new Analysis();
        analyst.regionAnalysis("Region_1_ID");
        analyst.regionAnalysis("Region_1_ID");
        analyst.regionAnalysis("Region_2_ID");
        analyst.regionAnalysis("Region_2_ID");
        analyst.regionAnalysis("Region_3_ID");

        boolean result = analyst.
                         getRegionsWithLeastActivities().
                         contains("Region_3_ID");

        assertEquals(true, result, "Region_3 should be in the array containing the regions with least activities.");
    }

    @Test
    public void getRegionWithMostActivitiesTest(){
        Analysis analyst = new Analysis();
        analyst.regionAnalysis("Region_1_ID");
        analyst.regionAnalysis("Region_1_ID");
        analyst.regionAnalysis("Region_2_ID");
        analyst.regionAnalysis("Region_2_ID");
        analyst.regionAnalysis("Region_3_ID");

        boolean result = analyst.
                         getRegionsWithMostActivities().
                         contains("Region_2_ID");

        assertEquals(true, result, "Region_2 should be in the array containing the regions with most activities.");
    }

}