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

        assertEquals(true, result, "boh");
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

        assertEquals(true, result, "boh");
    }

    @Test
    public void regionAnalysisTest(){
        Analysis analyst = new Analysis();
        analyst.regionAnalysis("1");
        
        boolean result = analyst.
                         getRegionActivities().
                         containsKey("1");

        assertEquals(true, result, "boh");
    }

    @Test
    public void getMostActivitiesTest(){
        Analysis analyst = new Analysis();
        analyst.regionAnalysis("1");
        analyst.regionAnalysis("1");
        analyst.regionAnalysis("2");

        int result = analyst.getMostActivities();
        assertEquals(2, result, "The expected result is 2.");
    }

    @Test
    public void getLeastActivitiesTest(){
        Analysis analyst = new Analysis();
        analyst.regionAnalysis("1");
        analyst.regionAnalysis("1");
        analyst.regionAnalysis("2");

        int result = analyst.getLeastActivities();
        assertEquals(1, result, "The expected result is 1.");
    }

    @Test
    public void getRegionWithLeastActivitiesTest(){
        Analysis analyst = new Analysis();
        analyst.regionAnalysis("1");
        analyst.regionAnalysis("1");
        analyst.regionAnalysis("2");
        analyst.regionAnalysis("2");
        analyst.regionAnalysis("3");

        boolean result = analyst.
                         getRegionsWithLeastActivities().
                         contains("3");

        assertEquals(true, result, "The expected result is 3.");
    }

    @Test
    public void getRegionWithMostActivitiesTest(){
        Analysis analyst = new Analysis();
        analyst.regionAnalysis("1");
        analyst.regionAnalysis("1");
        analyst.regionAnalysis("2");
        analyst.regionAnalysis("2");
        analyst.regionAnalysis("3");

        boolean result = analyst.
                         getRegionsWithMostActivities().
                         contains("2");

        assertEquals(true, result, "The expected result is .");
    }

}