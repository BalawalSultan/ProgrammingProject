package pp201920.project.a5;

import java.util.Vector;

public class ActivityVector{
    
    Vector<Activity> list;

    public ActivityVector(int size){
        this.list = new Vector<Activity>(size);
    }

    public void addActivity(Activity activity){
        this.list.add(activity);
    }

    public Vector<Activity> getVector(){
        return this.list;
    }
    
    public int getSize(){
        return this.list.size();
    }

}