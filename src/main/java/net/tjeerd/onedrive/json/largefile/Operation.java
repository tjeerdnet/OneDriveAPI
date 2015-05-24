package net.tjeerd.onedrive.json.largefile;

import java.util.Map;

import com.fasterxml.jackson.annotation.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Operation {
    private Application application;
    private Map<String, String> user;
    
    public Application getApplication() {
        return this.application;
    }
    public void setApplication(Application application) {
        this.application = application;
    }
    public Map<String, String> getUser() {
        return this.user;
    }
    public void setUser(Map<String, String> user) {
        this.user = user;
    }
}
