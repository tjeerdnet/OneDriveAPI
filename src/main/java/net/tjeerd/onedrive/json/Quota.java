package net.tjeerd.onedrive.json;

public class Quota {
    long quota;
    long available;
    
    public long getQuota() {
        return quota;
    }
    
    public void setQuota(long quota) {
        this.quota = quota;
    }
    
    public long getAvailable() {
        return available;
    }
    
    public void setAvailable(long available) {
        this.available = available;
    }
    
}
