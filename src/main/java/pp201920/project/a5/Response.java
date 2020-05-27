package pp201920.project.a5;

import java.util.Vector;

public class Response{
    
    Vector<String> results;

    public Response(int size){
        this.results = new Vector<String>(size);
    }

    public synchronized void addResult(String result){
        this.results.add(result);
    }

    public Vector<String> getResults(){
        return this.results;
    }

}