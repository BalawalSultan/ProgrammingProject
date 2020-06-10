package pp201920.project.a5.loggingTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Runner {
    static final Logger logger = LogManager.getLogger();
    
    public static void main(String[] args) {
        Gson gson_a = new GsonBuilder().create();
        Gson gson_b = new GsonBuilder().setPrettyPrinting().create();

        try{ 
            MyTestClass a = new MyTestClass();
            a.error();

        }catch (Exception e) {
            logger.info("Normal error log");
            logger.error(e);

            logger.info("Error log with stackTrace");
            logger.error(e.getStackTrace());

            logger.info("Now see the json version without pretty print");
            logger.error(gson_a.toJson(e));

            logger.info("Now see the json version with pretty print");
            logger.error(gson_b.toJson(e));
        }

    }

}