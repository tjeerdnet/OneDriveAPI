package net.tjeerd.onedrive.json.folder;

import java.util.ArrayList;
import java.util.List;


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
    private long        size;
    private String     source;
    private String     type;
    private String     updated_time;
    private String     upload_location;
    
    // Fileds for music files
    private String	title;
    private String	artist;
    private String	album;
    private String	album_artist;
    private String	genre;
    private int		duration;
    private String	picture;
    private int		tags_count; 
    private boolean	tags_enabled;
    private List<MusicFileImages>	images = new ArrayList<MusicFileImages>();
    
    //Fields for image files
    private String	when_taken;
    private int		height; 
    private int		width; 
    private PhotoLocation	location;
    private String	camera_make; 
    private String	camera_model; 
    private String	focal_ratio; 
    private String	focal_length; 
    private String	exposure_numerator; 
    private String	exposure_denominator; 
    
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
    
    public long getSize() {
        return this.size;
    }
    
    public void setSize(long size) {
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getAlbum() {
		return album;
	}

	public void setAlbum(String album) {
		this.album = album;
	}

	public String getAlbum_artist() {
		return album_artist;
	}

	public void setAlbum_artist(String album_artist) {
		this.album_artist = album_artist;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public int getTags_count() {
		return tags_count;
	}

	public void setTags_count(int tags_count) {
		this.tags_count = tags_count;
	}

	public boolean isTags_enabled() {
		return tags_enabled;
	}

	public void setTags_enabled(boolean tags_enabled) {
		this.tags_enabled = tags_enabled;
	}

	public String getWhen_taken() {
		return when_taken;
	}

	public void setWhen_taken(String when_taken) {
		this.when_taken = when_taken;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public PhotoLocation getLocation() {
		return location;
	}

	public void setLocation(PhotoLocation location) {
		this.location = location;
	}

	public String getCamera_make() {
		return camera_make;
	}

	public void setCamera_make(String camera_make) {
		this.camera_make = camera_make;
	}

	public String getCamera_model() {
		return camera_model;
	}

	public void setCamera_model(String camera_model) {
		this.camera_model = camera_model;
	}

	public String getFocal_ratio() {
		return focal_ratio;
	}

	public void setFocal_ratio(String focal_ratio) {
		this.focal_ratio = focal_ratio;
	}

	public String getFocal_length() {
		return focal_length;
	}

	public void setFocal_length(String focal_length) {
		this.focal_length = focal_length;
	}

	public String getExposure_numerator() {
		return exposure_numerator;
	}

	public void setExposure_numerator(String exposure_numerator) {
		this.exposure_numerator = exposure_numerator;
	}

	public String getExposure_denominator() {
		return exposure_denominator;
	}

	public void setExposure_denominator(String exposure_denominator) {
		this.exposure_denominator = exposure_denominator;
	}

	public List<MusicFileImages> getImages() {
		return images;
	}

	public void setImages(List<MusicFileImages> images) {
		this.images = images;
	}


}
