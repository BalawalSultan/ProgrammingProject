package pp201920.project.a5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * This class is designed to create an analysis object
 * which is updated with each activity object.
 *
 * @author Sultan Balawal
 * @author Alberto Nicoletti
 * @author René Zorzi
 *
 * @version 1.0
 */

public class Analysis{
    HashMap<String, Integer> regionActivities, activityTypes;
    ArrayList<String> trackedActivityIds;

    public Analysis(){
        regionActivities = new HashMap<>();
        activityTypes = new HashMap<>();
        trackedActivityIds = new ArrayList<>();
    }

    /**
     * This method accepts an activity as parameter and updates the results
     * stored in the analysis with the values retrieved from the activity object,
     * if needed.
     *
     * @param activity
     */

    public void performAnalysis(Activity activity){
        hasGPSTrackAnalysis (
            activity.getHasGPSTrack(),
            activity.getId()
        );   

        activityTypesAnalysis(activity.getTypes());
        regionAnalysis(activity.getRegionId());
    }

    /**
     * Checks whether the activity currently inspected has GPSTrack,
     * if so it stores the id of the activity in the trackedActivityIds list.
     *
     * @param activityHasGPSTrack
     * @param Id
     */

    public void hasGPSTrackAnalysis(boolean activityHasGPSTrack, String Id){
        if(activityHasGPSTrack)
            trackedActivityIds.add(Id);
    }

    /**
     * 
     * @param types
     */

    public void activityTypesAnalysis(String[] types){
        for (String type : types) {
            if(type != null){
                if(activityTypes.containsKey(type))
                    activityTypes.put(type, activityTypes.get(type) + 1);
                else
                    activityTypes.put(type, 1);
            }
        }
    }

    public void regionAnalysis(String Id){

        if(regionActivities.containsKey(Id))
            regionActivities.put(Id, regionActivities.get(Id) + 1);
        else
            regionActivities.put(Id, 1);

    }

    public int getMostActivities(){
        return Collections.max(regionActivities.values());
    }

    public int getLeastActivities(){
        return Collections.min(regionActivities.values());
    }

    public ArrayList<String> getRegionsWithMostActivities(){
        ArrayList<String> regions = new ArrayList<>();
        int max = getMostActivities();
        
        regionActivities.forEach((key, value) -> {
            if (value == max)
                regions.add(key);
        });

        return regions;
    }

    public ArrayList<String> getRegionsWithLeastActivities(){
        ArrayList<String> regions = new ArrayList<>();
        int min = getLeastActivities();
        
        regionActivities.forEach((key, value) -> {
            if (value == min)
                regions.add(key);
        });

        return regions;
    }

    public ArrayList<String> getTrackedActivityIds(){
        return trackedActivityIds;
    }

    public HashMap<String, Integer> getActivitiesTypes(){
        return activityTypes;
    }

    public HashMap<String, Integer> getRegionActivities() {
        return regionActivities;
    }

}