package pp201920.project.a5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Analysis{
    HashMap<String, Integer> regionActivities, activityTypes;
    ArrayList<String> trackedActivityIds;

    public Analysis(){
        regionActivities = new HashMap<>();
        activityTypes = new HashMap<>();
        trackedActivityIds = new ArrayList<>();
    }

    public void performAnalysis(Activity activity){
        hasGPSTrackAnalysis (
            activity.getHasGPSTrack(),
            activity.getId()
        );   

        activityTypesAnalysis(activity.getTypes());
        regionAnalysis(activity.getRegionId());
    }

    public void hasGPSTrackAnalysis(boolean activityHasGPSTrack, String Id){
        if(activityHasGPSTrack)
            trackedActivityIds.add(Id);
    }

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