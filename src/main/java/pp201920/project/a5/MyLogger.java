package pp201920.project.a5;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.logging.log4j.Logger;

public class MyLogger {
    static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    Logger logger;

    public MyLogger(){
        super();
    }
    
    public MyLogger(Logger logger){
        this.logger = logger;
    }

    public void info(Object obj){
        String message = gson.toJson(obj);
        logger.info(message);
    }

    public void error(Object obj){
        String message = gson.toJson(obj);
        logger.error(message);
    }

    public void warn(Object obj){
        String message = gson.toJson(obj);
        logger.warn(message);
    }

    public void debug(Object obj){
        String message = gson.toJson(obj);
        logger.debug(message);
    }
}