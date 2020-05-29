package pp201920.project.a5;

import java.util.ArrayList;

public class ActivityList{
    
    ArrayList<Activity> list;

    public ActivityList(int size){
        this.list = new ArrayList<Activity>(size);
    }

    public void addActivity(Activity activity){
        this.list.add(activity);
    }

    public  ArrayList<Activity> getList(){
        return this.list;
    }
    
    public int getSize(){
        return this.list.size();
    }

}