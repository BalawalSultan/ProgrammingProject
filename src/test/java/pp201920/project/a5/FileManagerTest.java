package pp201920.project.a5;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;

public class FileManagerTest {

    FileManager fileManager = new FileManager("src/test/resources/");
    String pathToTestFile,message;

    @Test
    public void getNumOfObjectsTest() throws IOException {
        pathToTestFile = "FileManagerTest/TestInput.txt";
        int result = fileManager.readNumOfObjects(pathToTestFile);

        assertEquals(12,result,"The file \"TestInput.txt\" should contain the number 12");
    }

    @Test
    public void getNumOfObjectsDouble() {
        pathToTestFile = "FileManagerTest/TestInputDouble.txt";
        message = "The file \"TestInputDouble.txt\" should throw a NumberFormatException";

        assertThrows(NumberFormatException.class,()->fileManager.readNumOfObjects(pathToTestFile),message);
    }

    @Test
    public void getNumOfObjectsNull() {
        pathToTestFile = "FileManagerTest/TestInputNull.txt";
        message = "The file \"TestInputNull.txt\" should throw a IllegalArgumentException";

        assertThrows(IllegalArgumentException.class,()->fileManager.readNumOfObjects(pathToTestFile),message);
    }

    @Test
    public void getNumOfObjectsString(){
        pathToTestFile = "FileManagerTest/TestInputString.txt";
        message = "The file \"TestInputString.txt\" should throw a NumberFormatException";

        assertThrows(NumberFormatException.class,()->fileManager.readNumOfObjects(pathToTestFile),message);
    }

}
