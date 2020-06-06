package pp201920.project.a5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileManagerTest {

    FileManager fileManager = new FileManager();


    @Test
    public void getNumOfObjectsTest() {

        assertEquals(12,fileManager.getNumOfObjects("src/test/resources/FileManagerTest/TestInput.txt"));

    }

    @Test
    public void getNumOfObjectsDouble() {

        assertEquals(17,fileManager.getNumOfObjects("src/test/resources/FileManagerTest/TestInputDouble.txt"));

    }

    /*@Test
    public void getNumOfObjectsNull() {

        assertEquals(0,fileManager.getNumOfObjects("src/test/resources/FileManagerTest/TestInputNull.txt"));

    }

    @Test
    public void getNumOfObjectsString(){



    }

     */

}
