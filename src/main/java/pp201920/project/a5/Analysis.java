package pp201920.project.a5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Analysis{
    HashMap<String, Integer> regionActivities, activityTypes;
    ArrayList<String> trackedActivityIds;

    public Analysis(){
        this.regionActivities = new HashMap<String, Integer>();
        this.activityTypes = new HashMap<String, Integer>();
        this.trackedActivityIds = new ArrayList<String>();
    }

    public void performAnalysis(Activity activity){
        if(activity.getHasGPSTrack())
            trackedActivityIds.add(activity.getId());

        activityTypesAnalysis(activity);
        regionAnalysis(activity);
    }

    public void showAnalysisResult(){
        System.out.println("activitiesTypes{");
        this.activityTypes.entrySet().forEach(entry->{
            System.out.println("\t" + entry.getKey() + ": " + entry.getValue());
        });System.out.println("}");

        System.out.println("trackedActivityIds[");
        for (String id : trackedActivityIds) {
            System.out.println("\t" + id);
        }System.out.println("]");

        System.out.println("The regions with " + getMostActivities() +
                           " activities are: " + getRegionsWithMostActivities());

        System.out.println("The regions with " + getLeastActivities() +
                           " activities are: " + getRegionsWithLeastActivities());
    }

    public void activityTypesAnalysis(Activity activity){
        String[] typeStrings = activity.getTypes();

        for (String type : typeStrings) {
            if(type != null){
                if(this.activityTypes.containsKey(type) == false)
                    this.activityTypes.put(type,0);
                else
                    this.activityTypes.put(type, activityTypes.get(type) + 1);
            }
        }
    }

    public void regionAnalysis(Activity activity){
        String region = activity.getRegion();
        int activity_number = activity.getTypes().length;

        if(region != null){
            if(this.regionActivities.containsKey(region) == false)
                this.regionActivities.put(region,activity_number);
            else
                this.regionActivities.put(region, regionActivities.get(region) + activity_number);
        }
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