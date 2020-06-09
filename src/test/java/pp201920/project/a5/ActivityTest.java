package pp201920.project.a5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        String id = null;
        activity.setId(id);

        assertEquals(id, activity.getId());
    }

    public void nameTest(){
        String name = "Hello";
        activity.setName(name);

        assertEquals(name, activity.getName());
    }

    public void nameIsEmptyTest(){
        String name = "";
        activity.setName(name);

        assertEquals(name, activity.getName());
    }

    public void nameIsNullTest(){
        String name = null;
        activity.setName(name);

        assertEquals(name, activity.getName());
    }

    @Test
    public void descriptionTest(){
        String description = "Sky Is blue";
        activity.setDescription(description);

        boolean result = activity.
                         getDescription().
                         equals("Sky Is blue");

        assertEquals(true, result);
    }

    @Test
    public void descriptionIsEmptyTest(){
        String description  = "";
        activity.setDescription(description);

        boolean result = activity.
                         getDescription().
                         isEmpty();

        assertEquals(true, result);
    }

    @Test
    public void descriptionIsNull(){
        String description = null;
        activity.setDescription(description);

        assertEquals(null, activity.getDescription());
    }

    @Test
    public void regionTest(){
        String region = "Camembert";
        activity.setRegion(region);

        boolean result = activity.
                         getRegion().
                         equals("Camembert");

        assertEquals(true, result);
    }

    @Test
    public void regionIsEmptyTest(){
        String region = "";
        activity.setRegion(region);

        boolean result = activity.
                         getRegion().
                         isEmpty();

        assertEquals(true, result);
    }

    @Test
    public void regionIsNullTest(){
        String region = null;
        activity.setRegion(region);

        assertEquals(null, activity.getRegion());
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
        String[] types = null;
        activity.setTypes(types);

        assertEquals(types, activity.getTypes());
    }

    @Test
    public void HasGPSTrackTest(){
        activity.setHasGPSTrack(true);
        assertEquals(true, activity.getHasGPSTrack());
    }

    public void HasNoGPSTrackTest(){
        activity.setHasGPSTrack(false);
        assertEquals(false, activity.getHasGPSTrack());
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
        String regionId = null;
        activity.setRegionId(regionId);

        assertEquals(null, activity.getRegionId());
    }
}
