package pp201920.project.a5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Analysis{
    HashMap<String, Integer> regionActivities, activityTypes;
    ArrayList<String> trackedActivityIds;

    public Analysis(){
        regionActivities = new HashMap<String, Integer>();
        activityTypes = new HashMap<String, Integer>();
        trackedActivityIds = new ArrayList<String>();
    }

    public void performAnalysis(Activity activity){
        if(activity.getHasGPSTrack())
            trackedActivityIds.add(activity.getId());

        activityTypesAnalysis(activity);
        regionAnalysis(activity);
    }

    public void activityTypesAnalysis(Activity activity){
        String[] typeStrings = activity.getTypes();

        for (String type : typeStrings) {
            if(type != null){
                if(activityTypes.containsKey(type))
                    activityTypes.put(type, activityTypes.get(type) + 1);
                else
                    activityTypes.put(type, 1);
            }
        }
    }

    public void regionAnalysis(Activity activity){
        String Id = activity.getRegionId();
        int activity_number = activity.getTypes().length;

        if(regionActivities.containsKey(Id))
            regionActivities.put(Id, regionActivities.get(Id) + activity_number);
        else
            regionActivities.put(Id, activity_number);

    }

    public int getMostActivities(){
        return Collections.max(regionActivities.values());
    }

    public int getLeastActivities(){
        return Collections.min(regionActivities.values());
    }

    public ArrayList<String> getRegionsWithMostActivities(){
        ArrayList<String> regions = new ArrayList<String>();
        int max = getMostActivities();
        
        regionActivities.entrySet().forEach(entry->{
            if(entry.getValue() == max)
                regions.add(entry.getKey());
        });

        return regions;
    }

    public ArrayList<String> getRegionsWithLeastActivities(){
        ArrayList<String> regions = new ArrayList<String>();
        int min = getLeastActivities();
        
        regionActivities.entrySet().forEach(entry->{
            if(entry.getValue() == min)
                regions.add(entry.getKey());
        });

        return regions;
    }

    public ArrayList<String> getTrackedActivityIds(){
        return trackedActivityIds;
    }

    public HashMap<String, Integer> getActivitiesTypes(){
        return activityTypes;
    }

}