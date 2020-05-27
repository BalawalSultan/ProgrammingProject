package pp201920.project.a5;

import java.util.Vector;

public class ActivityList {
    Vector<Activity> list;

    public ActivityList(int size){
        this.list = new Vector<Activity>(size);
    }

    public synchronized void addActivity(Activity activity){
        this.list.add(activity);
    }

    public Vector<Activity> getActivities(){
        return this.list;
    }

}