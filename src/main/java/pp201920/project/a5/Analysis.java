package pp201920.project.a5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * This class is designed to create an analysis object
 * which is updated with each activity object.
 * Since the class is designed to analyze one Activity object at
 * a time, in the comments the analyzed activity object will be referred
 * as "the Activity".
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
     * stored in the analysis with the values retrieved from the Activity object,
     * if needed.
     *
     * @param activity the Activity object to be analyzed.
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
     * Checks whether the Activity currently inspected has GPSTrack,
     * if so it stores the id of the Activity in the trackedActivityIds list.
     *
     * @param activityHasGPSTrack the boolean stating whether the Activity has some GPSTrack data.
     * @param Id the string containing the id of the Activity.
     */

    public void hasGPSTrackAnalysis(boolean activityHasGPSTrack, String Id){
        if(activityHasGPSTrack)
            trackedActivityIds.add(Id);
    }

    /**
     * Checks whether, among the types contained in the Activity, a specific type
     * is already contained in the analysis, if so, increases the counter by 1,
     * otherwise it adds the new type to the map.
     *
     * @param types the array of strings containing the types of the Activity
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

    /**
     * Checks whether the region of the Activity is already contained in the analysis,
     * if so, it increments the counter by one, otherwise it adds the region id
     * to the regionActivities map.
     *
     * @param Id the string containing the Id of the region of the Activity
     */
    public void regionAnalysis(String Id){

        if(regionActivities.containsKey(Id))
            regionActivities.put(Id, regionActivities.get(Id) + 1);
        else
            regionActivities.put(Id, 1);

    }

    /**
     * Searches among the whole regionActivities map to gather
     * the highest integer value.
     *
     * @return the highest number of times a region was counted.
     */

    public int getMostActivities(){
        return Collections.max(regionActivities.values());
    }

    /**
     * Searches among the whole regionActivities map to gather
     * the lowest integer value.
     * 
     * @return the lowest number of times a region was counted.
     */

    public int getLeastActivities(){
        return Collections.min(regionActivities.values());
    }

    /**
     * Searches through all entries of the regionActivities map
     * to find which entry has the highest integer value.
     *
     * @return an ArrayList of strings containing the Ids of the regions with highest integer value.
     */


    public ArrayList<String> getRegionsWithMostActivities(){
        ArrayList<String> regions = new ArrayList<>();
        int max = getMostActivities();
        
        regionActivities.forEach((key, value) -> {
            if (value == max)
                regions.add(key);
        });

        return regions;
    }

    /**
     * Searches through all entries of the regionActivities map
     * to find which entry has the lowest integer value.
     *
     * @return an ArrayList of strings containing the Ids of the regions with lowest integer value.
     */

    public ArrayList<String> getRegionsWithLeastActivities(){
        ArrayList<String> regions = new ArrayList<>();
        int min = getLeastActivities();
        
        regionActivities.forEach((key, value) -> {
            if (value == min)
                regions.add(key);
        });

        return regions;
    }

    /**
     * A simple getter.
     *
     * @return the list of the Ids of the Activities analyzed.
     */

    public ArrayList<String> getTrackedActivityIds(){
        return trackedActivityIds;
    }

    /**
     * A simple getter
     *
     * @return the map of the types of the analyzed activities and how many times they were encountered.
     */

    public HashMap<String, Integer> getActivitiesTypes(){
        return activityTypes;
    }

    /**
     * A simple getter.
     *
     * @return the map of the region Ids and how many time an Activity was to be held in there.
     */

    public HashMap<String, Integer> getRegionActivities() {
        return regionActivities;
    }

}