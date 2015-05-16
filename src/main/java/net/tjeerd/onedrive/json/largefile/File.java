package net.tjeerd.onedrive.json.largefile;

import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class File {
    private String mimeType;
    private Map<String, String> hash;
    
    public String getMimeType() {
        return this.mimeType;
    }
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
    public Map<String, String> getHash() {
        return this.hash;
    }
    public void setHash(Map<String, String> hash) {
        this.hash = hash;
    }
}
