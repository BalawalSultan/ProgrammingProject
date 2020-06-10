package pp201920.project.a5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion.VersionFlag;
import com.networknt.schema.ValidationMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JsonSchemaValidator {

    private final JsonSchema activitySchema,analysisSchema;
    final Logger logging = LogManager.getLogger();
    final MyLogger logger = new MyLogger(logging);

    public JsonSchemaValidator(String pathToResources){
        ObjectMapper mapper = new ObjectMapper();
        JsonNode activityNode = null, analysisNode = null;

        try{
            activityNode = mapper.readTree(readFile(pathToResources + "activity.schema.json"));
            analysisNode = mapper.readTree(readFile(pathToResources + "analysis.schema.json"));

        }catch(IOException e){
            logger.error("Something went wrong while reading the schema json");
            e.printStackTrace();

        }

        JsonSchemaFactory factory = JsonSchemaFactory.getInstance(VersionFlag.V7);

        activitySchema = factory.getSchema(activityNode);
        analysisSchema = factory.getSchema(analysisNode);
    }

    public boolean validateSchema(String json, int option){
        Set<ValidationMessage> errors;
        JsonNode node = null;

        try{
            node = (new ObjectMapper()).readTree(json);
        }catch(IOException e){
            logger.error("Failed to read "+json);
            e.printStackTrace();
        }

        if(option == 0)
            errors = activitySchema.validate(node);
        else
            errors = analysisSchema.validate(node);


        return errors.size() == 0;
    }

    private String readFile(String path){
        StringBuilder fileContent = new StringBuilder();
        File file = new File(path);
        
        try(FileReader fileReader = new FileReader(file)){
            BufferedReader reader = new BufferedReader(fileReader);
            String line = reader.readLine();

            while(line != null){
                fileContent.append(line);
                line = reader.readLine();
            }

            reader.close();

        } catch (IOException e) {
            logger.error("Failed to read "+path);
            e.printStackTrace();
        }
        
        return fileContent.toString();
    }

}