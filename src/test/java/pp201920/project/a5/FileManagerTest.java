package pp201920.project.a5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class FileManagerTest {

    FileManager fileManager = new FileManager();
    String path,message;



    @Test
    public void getNumOfObjectsTest() throws IOException {

        path = "src/test/resources/FileManagerTest/TestInput.txt";
        int result = fileManager.readNumOfObjects(path);
        assertEquals(12,result,"The file \"TestInput.txt\" should contain the number 12");

    }

    @Test
    public void getNumOfObjectsDouble() {

        path = "src/test/resources/FileManagerTest/TestInputDouble.txt";
        message = "The file \"TestInputDouble.txt\" should throw a NumberFormatException";
        assertThrows(NumberFormatException.class,()->fileManager.readNumOfObjects(path),message);

    }

    @Test
    public void getNumOfObjectsNull() {

        path = "src/test/resources/FileManagerTest/TestInputNull.txt";
        message = "The file \"TestInputNull.txt\" should throw a IllegalArgumentException";
        assertThrows(IllegalArgumentException.class,()->fileManager.readNumOfObjects(path),message);

    }

    @Test
    public void getNumOfObjectsString(){

        path = "src/test/resources/FileManagerTest/TestInputString.txt";
        message = "The file \"TestInputDouble.txt\" should throw a NumberFormatException";
        assertThrows(NumberFormatException.class,()->fileManager.readNumOfObjects(path),message);


    }

}
