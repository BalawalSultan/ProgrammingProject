package pp201920.project.a5;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileManagerTest {

    FileManager fileManager = new FileManager();


    @Test
    public void getNumOfObjectsTest() throws IOException {

        assertEquals(12,fileManager.getNumOfObjects("src/test/resources/FileManagerTest/TestInput.txt"));

    }

    @Test
    public void getNumOfObjectsDouble() {

        assertThrows(NumberFormatException.class,()->fileManager.getNumOfObjects("src/test/resources/FileManagerTest/TestInputDouble.txt"));

    }

    @Test
    public void getNumOfObjectsNull() {

        assertThrows(IllegalArgumentException.class,()->fileManager.getNumOfObjects("src/test/resources/FileManagerTest/TestInputNull.txt"));

    }

    @Test
    public void getNumOfObjectsString(){

        assertThrows(NumberFormatException.class,()->fileManager.getNumOfObjects("src/test/resources/FileManagerTest/TestInputString.txt"));

    }

}
