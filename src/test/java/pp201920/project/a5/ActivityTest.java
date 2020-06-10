package pp201920.project.a5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ActivityTest {

    Activity activity = new Activity();

    @Test
    public void idTest(){
        String id = "1234567890A";
        activity.setId(id);

        assertEquals(id, activity.getId());
    }

    @Test
    public void idIsEmptyTest(){
        String id = "";
        activity.setId(id);

        assertEquals(id, activity.getId());
    }

    @Test
    public void idIsNullTest(){
        activity.setId(null);

        assertNull(activity.getId());
    }

    @Test
    public void nameTest(){
        String name = "Hello";
        activity.setName(name);

        assertEquals(name, activity.getName());
    }

    @Test
    public void nameIsEmptyTest(){
        String name = "";
        activity.setName(name);

        assertEquals(name, activity.getName());
    }

    @Test
    public void nameIsNullTest(){
        activity.setName(null);

        assertNull(activity.getName());
    }

    @Test
    public void descriptionTest(){
        String description = "Sky Is blue";
        activity.setDescription(description);

        boolean result = activity.
                         getDescription().
                         equals("Sky Is blue");

        assertTrue(result);
    }

    @Test
    public void descriptionIsEmptyTest(){
        String description  = "";
        activity.setDescription(description);

        boolean result = activity.
                         getDescription().
                         isEmpty();

        assertTrue(result);
    }

    @Test
    public void descriptionIsNull(){
        activity.setDescription(null);

        assertNull(activity.getDescription());
    }

    @Test
    public void regionTest(){
        String region = "Camembert";
        activity.setRegion(region);

        boolean result = activity.
                         getRegion().
                         equals("Camembert");

        assertTrue(result);
    }

    @Test
    public void regionIsEmptyTest(){
        String region = "";
        activity.setRegion(region);

        boolean result = activity.
                         getRegion().
                         isEmpty();

        assertTrue(result);
    }

    @Test
    public void regionIsNullTest(){
        activity.setRegion(null);

        assertNull(activity.getRegion());
    }

    @Test
    public void typesTest(){
        String[] types ={
            "Ciao",
            "Mirror",
            "Quarantine",
            "JustSomeR4ndomWords"
        };

        activity.setTypes(types);

        assertEquals(types, activity.getTypes());
    }

    @Test
    public void typesIsEmptyTest(){
        String[] types = new String[0];
        activity.setTypes(types);

        assertEquals(types, activity.getTypes());
    }

    @Test
    public void typesIsNullTest(){
        activity.setTypes(null);

        assertNull(activity.getTypes());
    }

    @Test
    public void HasGPSTrackTest(){
        activity.setHasGPSTrack(true);
        assertTrue(activity.getHasGPSTrack());
    }

    @Test
    public void HasNoGPSTrackTest(){
        activity.setHasGPSTrack(false);
        assertFalse(activity.getHasGPSTrack());
    }

    @Test
    public void regionIdTest(){
        String regionId = "12234563789DF7";
        activity.setRegionId(regionId);

        assertEquals("12234563789DF7", activity.getRegionId());
    }

    @Test
    public void regionIdIsEmptyTest(){
        String regionId = "";
        activity.setRegionId(regionId);

        assertEquals("", activity.getRegionId());
    }

    @Test
    public void regionIdIsNullTest(){
        activity.setRegionId(null);

        assertNull(activity.getRegionId());
    }
}
