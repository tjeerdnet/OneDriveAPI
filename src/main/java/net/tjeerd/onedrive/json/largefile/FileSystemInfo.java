package net.tjeerd.onedrive.json.largefile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FileSystemInfo {
    // FIXME: Couldn't convert 2015-05-16T03:26:04.61+00:00 to java.util.Date
    private String createdDateTime;
    private String lastModifiedDateTime;
    
    public String getCreatedDateTime() {
        return this.createdDateTime;
    }
    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
    public String getLastModifiedDateTime() {
        return this.lastModifiedDateTime;
    }
    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }
}
