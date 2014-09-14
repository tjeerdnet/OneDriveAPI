package net.tjeerd.onedrive.json.folder;

public class Data  {
    private String     client_updated_time;
    private Number     comments_count;
    private boolean    comments_enabled;
    private String     created_time;
    private String     description;
    private From       from;
    private String     id;
    private boolean    is_embeddable;
    private int        count;
    private String     link;
    private String     name;
    private String     parent_id;
    private SharedWith shared_with;
    private int        size;
    private String     source;
    private String     type;
    private String     updated_time;
    private String     upload_location;
    
    public Data(String id) {
        this.id = id;
    }
    
    public Data() {
    }
    
    public String getClient_updated_time() {
        return this.client_updated_time;
    }
    
    public void setClient_updated_time(String client_updated_time) {
        this.client_updated_time = client_updated_time;
    }
    
    public Number getComments_count() {
        return this.comments_count;
    }
    
    public void setComments_count(Number comments_count) {
        this.comments_count = comments_count;
    }
    
    public boolean getComments_enabled() {
        return this.comments_enabled;
    }
    
    public void setComments_enabled(boolean comments_enabled) {
        this.comments_enabled = comments_enabled;
    }
    
    public String getCreated_time() {
        return this.created_time;
    }
    
    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public From getFrom() {
        return this.from;
    }
    
    public void setFrom(From from) {
        this.from = from;
    }
    
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public boolean getIs_embeddable() {
        return this.is_embeddable;
    }
    
    public void setIs_embeddable(boolean is_embeddable) {
        this.is_embeddable = is_embeddable;
    }
    
    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getParent_id() {
        return this.parent_id;
    }
    
    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }
    
    public SharedWith getShared_with() {
        return this.shared_with;
    }
    
    public void setShared_with(SharedWith sharedWith) {
        this.shared_with = sharedWith;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public void setSize(int size) {
        this.size = size;
    }
    
    public String getSource() {
        return this.source;
    }
    
    public void setSource(String source) {
        this.source = source;
    }
    
    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public String getUpdated_time() {
        return this.updated_time;
    }
    
    public void setUpdated_time(String updated_time) {
        this.updated_time = updated_time;
    }
    
    public String getUpload_location() {
        return this.upload_location;
    }
    
    public void setUpload_location(String upload_location) {
        this.upload_location = upload_location;
    }
    
    public int getCount() {
        return count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
}
