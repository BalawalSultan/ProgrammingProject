package pp201920.project.a5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

public class ActivityMap {
    ConcurrentHashMap<String, Integer> map;

    public ActivityMap(){
        this.map = new ConcurrentHashMap<String, Integer>();
    }

    public synchronized void addActivity(Activity activity){
        String region = activity.getRegion();
        int activity_number = activity.getTypes().length;

        if(region != null){
            if(this.map.containsKey(region) == false)
                this.map.put(region,activity_number);
            else
                this.map.put(region, map.get(region) + activity_number);
        }
        
    }

    public ArrayList<String> getRegionsWithMostActivities(){
        int max = Collections.max(map.values());
        ArrayList<String> regions = new ArrayList<String>();
        
        this.map.entrySet().forEach(entry->{
            if(entry.getValue() == max)
                regions.add(entry.getKey());
        });

        return regions;
    }

    public ArrayList<String> getRegionsWithLeastActivities(){
        int min = Collections.min(map.values());
        ArrayList<String> regions = new ArrayList<String>();
        
        this.map.entrySet().forEach(entry->{
            if(entry.getValue() == min)
                regions.add(entry.getKey());
        });

        return regions;
    }

}