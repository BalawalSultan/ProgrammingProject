package pp201920.project.a5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActivityTest {




    Activity activity = new Activity();

    @Test
    public void testId(){
        String id = "1234567890A";

        activity.setId(id);
        assertEquals(id, activity.getId());

        id = "";
        activity.setId(id);
        assertEquals(id, activity.getId());

        id = null;
        activity.setId(id);
        assertEquals(id, activity.getId());

        id = String.valueOf(123);
        activity.setId(id);
        assertEquals(id, activity.getId());
    }

    @Test
    public void testName(){
        String name = "Hello";
        activity.setName(name);
        assertEquals(name, activity.getName());

        name = "";
        activity.setName(name);
        assertEquals(name, activity.getName());

        name = null;
        activity.setName(name);
        assertEquals(name, activity.getName());
    }

    @Test
    public void testDescription(){
        String description = "Sky Is blue";
        activity.setDescription(description);
        assertEquals(description, activity.getDescription());

        description = "";
        activity.setDescription(description);
        assertEquals(description, activity.getDescription());

        description = null;
        activity.setDescription(description);
        assertEquals(description, activity.getDescription());
    }

    @Test
    public void testRegion(){
        String region = "Camembert";
        activity.setRegion(region);
        assertEquals(region, activity.getRegion());

        region = "";
        activity.setRegion(region);
        assertEquals(region, activity.getRegion());

        region = null;
        activity.setRegion(region);
        assertEquals(region, activity.getRegion());
    }

    @Test
    public void testTypes(){

        String[] types =
                {
                        "Ciao",
                        "Mirror",
                        "Quarantine",
                        "JustSomeR4ndomWords"
                };
        activity.setTypes(types);
        assertEquals(types, activity.getTypes());

        types = new String[]{};
        activity.setTypes(types);
        assertEquals(types, activity.getTypes());

        types = null;
        activity.setTypes(types);
        assertEquals(types, activity.getTypes());

    }

    @Test
    public void testHasGPSTrack(){
        boolean hasGPSTrack = false;
        activity.setHasGPSTrack(hasGPSTrack);
        assertEquals(hasGPSTrack, activity.getHasGPSTrack());

        hasGPSTrack = true;
        activity.setHasGPSTrack(true);
        assertEquals(hasGPSTrack, activity.getHasGPSTrack());
    }

    @Test
    public void testRegionId(){
        String regionId = "12234563789DF7";
        activity.setRegionId(regionId);
        assertEquals(regionId,activity.getRegionId());

        regionId = "";
        activity.setRegionId(regionId);
        assertEquals(regionId,activity.getRegionId());

        regionId = null;
        activity.setRegionId(regionId);
        assertEquals(regionId,activity.getRegionId());


    }
}
