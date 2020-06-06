package pp201920.project.a5;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

public class MyRequest{

    public MyRequest(){
        super();
    }

    public static String fetchAndHandle(URL url){
        String response = null;
        
        try{     
            response = fetch(url);

        }catch(SocketTimeoutException e){
            System.err.println("SocketTimeoutException!");
            e.printStackTrace();
            
        }catch(NullPointerException e){
            System.err.println("Empty Response!");
            e.printStackTrace();

        }catch(FileNotFoundException e){
            System.err.println("Error 404: FileNotFoundException!");
            e.printStackTrace();

        }catch(IOException e){
            System.err.println("IOException!");
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