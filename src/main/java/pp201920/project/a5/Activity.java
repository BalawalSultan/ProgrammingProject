package pp201920.project.a5;

public class Activity {

    String id, name, description, region;
    String[] types;
    boolean hasGPSTrack;

    public Activity(){
        super();
    }

    public Activity(String id, String name, String description, String region, String[] types, boolean hasGPSTrack) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.region = region;
        this.types = types;
        this.hasGPSTrack = hasGPSTrack;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }

    public boolean getHasGPSTrack() {
        return hasGPSTrack;
    }

    public void setHasGPSTrack(boolean hasGPSTrack) {
        this.hasGPSTrack = hasGPSTrack;
    }

}