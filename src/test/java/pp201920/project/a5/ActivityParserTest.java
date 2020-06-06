package pp201920.project.a5;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
public class ActivityParserTest {
    @Test
    public void myTest(){
        String json = "{"+
                "'BaseText':"+"'AAA'"+
                "}";

        JsonObject Detail = new JsonParser().
                parse(json).
                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        String description = parser.getDescription(Detail);

        assertEquals(true, description.equals("AAA"),description);
    }

    @Test
    public void myNullTest(){
        String json = "{"+
                "'BaseText':"+"null"+
                "}";

        JsonObject Detail = new JsonParser().
                parse(json).
                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        String description = parser.getDescription(Detail);

        assertEquals(true, (description == null),description);
    }

    @Test
    public void myEmptyTest(){
        String json = "{"+
                "'BaseText':"+"''"+
                "}";

        JsonObject Detail = new JsonParser().
                parse(json).
                getAsJsonObject();

        ActivityParser parser = new ActivityParser();
        String description = parser.getDescription(Detail);

        assertEquals(true, description.isEmpty(),description);
    }
}