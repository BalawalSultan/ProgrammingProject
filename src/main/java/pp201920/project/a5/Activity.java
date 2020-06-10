package pp201920.project.a5;

public class Activity {

    String id, name, description;
    String[] types;
    boolean hasGPSTrack;
    String region;
    transient String regionId;

    public Activity() {
        super();
    }

    public Activity(
        String id, String name, String description, String region,
        String[] types, boolean hasGPSTrack, String regionId){

        this.id = id;
        this.name = name;
        this.description = description;
        this.region = region;
        this.types = types;
        this.hasGPSTrack = hasGPSTrack;
        this.regionId = regionId;
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

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;

        if (!(obj instanceof Activity))
            return false;

        Activity activity = (Activity) obj;

        if (!id.equals(activity.getId()))
            return false;

        if (!name.equals(activity.getName()))
            return false;

        if (!description.equals(activity.getDescription()))
            return false;

        if (hasGPSTrack != activity.getHasGPSTrack())
            return false;

        if (!region.equals(activity.getRegion()))
            return false;

        if (!regionId.equals(activity.getRegionId()))
            return false;

        String[] tempTypes = activity.getTypes();
        if(tempTypes.length == types.length){
            for(int i = 0; i < types.length; i++){
                if(!types[i].equals(tempTypes[i]))
                    return false;
            }
        }else
            return false;

        return true;
    }

}