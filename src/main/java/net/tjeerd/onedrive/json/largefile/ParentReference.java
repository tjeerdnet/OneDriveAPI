package net.tjeerd.onedrive.json.largefile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ParentReference {
    private String driveId;
    private String id;
    private String path;
    
    public String getDriveId() {
        return this.driveId;
    }
    public void setDriveId(String driveId) {
        this.driveId = driveId;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPath() {
        return this.path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
