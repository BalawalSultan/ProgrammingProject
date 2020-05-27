package pp201920.project.a5;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Vector;

public class Request implements Runnable {

    URL url;

    public Request(String url, Vector<String> re){
        try{
            this.url = new URL(url);
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
    }

    public void run(){
        // try{
        //     synchronized(response){
                
        //     }
        // }catch(InterruptedException e){
        //     e.printStackTrace();
        // }
    }
    
}