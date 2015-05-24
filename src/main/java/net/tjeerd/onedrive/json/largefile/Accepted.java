package net.tjeerd.onedrive.json.largefile;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Accepted {
    // FIXME: Couldn't use java.utilDate class because an error occured when parsing '2015-05-16T03:26:04.61+00:00' 
    private String expirationDateTime;
    private List<String> nextExpectedRanges;
    
    public String getExpirationDateTime() {
        return this.expirationDateTime;
    }
    public void setExpirationDateTime(String expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }
    public List<String> getNextExpectedRanges() {
        return this.nextExpectedRanges;
    }
    public void setNextExpectedRanges(List<String> nextExpectedRanges) {
        this.nextExpectedRanges = nextExpectedRanges;
    }
}
