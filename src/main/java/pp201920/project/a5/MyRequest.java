package pp201920.project.a5;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class MyRequest{

    static final Logger logger = LogManager.getLogger();

    public MyRequest(){
        super();
    }

    public static String fetchAndHandle(URL url){
        String response = null;
        
        try{     
            response = fetch(url);

        }catch(SocketTimeoutException e){
            logger.error("SocketTimeoutException!");
            e.printStackTrace();
            
        }catch(NullPointerException e){
            logger.error("Empty Response!");
            e.printStackTrace();

        }catch(IOException e){
            logger.error("IOException!");
            e.printStackTrace();
        }

        return response;
    }

    public static String fetch(URL url) throws IOException{        
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000);
        connection.setReadTimeout(10000);

        int status = connection.getResponseCode();

        StringBuilder response = new StringBuilder();
        BufferedReader reader;
        String line;

        if (status > 299)
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        else
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        
        while ((line = reader.readLine()) != null)
            response.append(line);
            
        reader.close();

        if(response.toString().isEmpty())
            throw new NullPointerException();

        return response.toString();
    }
    
}