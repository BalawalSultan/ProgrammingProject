package pp201920.project.a5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class ActivityMap {
    HashMap<String, Integer> regionActivities, activityTypes;


    public ActivityMap(){
        this.regionActivities = new HashMap<String, Integer>();
    }

    public void addActivity(Activity activity){
        String region = activity.getRegion();
        int activity_number = activity.getTypes().length;

        if(region != null){
            if(this.regionActivities.containsKey(region) == false)
                this.regionActivities.put(region,activity_number);
            else
                this.regionActivities.put(region, regionActivities.get(region) + activity_number);
        }
        
    }

    public int getMostActivities(){
        return Collections.max(this.regionActivities.values());
    }

    public int getLeastActivities(){
        return Collections.min(this.regionActivities.values());
    }

    public ArrayList<String> getRegionsWithMostActivities(){
        ArrayList<String> regions = new ArrayList<String>();
        int max = getMostActivities();
        
        this.regionActivities.entrySet().forEach(entry->{
            if(entry.getValue() == max)
                regions.add(entry.getKey());
        });

        return regions;
    }

    public ArrayList<String> getRegionsWithLeastActivities(){
        ArrayList<String> regions = new ArrayList<String>();
        int min = getLeastActivities();
        
        this.regionActivities.entrySet().forEach(entry->{
            if(entry.getValue() == min)
                regions.add(entry.getKey());
        });

        return regions;
    }

}