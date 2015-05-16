package net.tjeerd.onedrive.json.largefile;

import java.net.URL;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatedLargeFile {

    private Operation createdBy;
    private String createdDateTime;
    private String cTag;
    private String eTag;
    private String id;
    private Operation lastModifiedBy;
    // FIXME: Couldn't use java.utilDate class because an error occured when parsing '2015-05-16T03:26:04.61+00:00' 
    private String lastModifiedDateTime;
    private String name;
    private ParentReference parentReference;
    private long size;
    private URL webUrl;
    private net.tjeerd.onedrive.json.largefile.File file;
    private FileSystemInfo fileSystemInfo;
    
    public Operation getCreatedBy() {
        return this.createdBy;
    }
    public void setCreatedBy(Operation createdBy) {
        this.createdBy = createdBy;
    }
    public String getCreatedDateTime() {
        return this.createdDateTime;
    }
    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }
    public String getcTag() {
        return this.cTag;
    }
    public void setcTag(String cTag) {
        this.cTag = cTag;
    }
    public String geteTag() {
        return this.eTag;
    }
    public void seteTag(String eTag) {
        this.eTag = eTag;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Operation getLastModifiedBy() {
        return this.lastModifiedBy;
    }
    public void setLastModifiedBy(Operation lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }
    public String getLastModifiedDateTime() {
        return this.lastModifiedDateTime;
    }
    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ParentReference getParentReference() {
        return this.parentReference;
    }
    public void setParentReference(ParentReference parentReference) {
        this.parentReference = parentReference;
    }
    public long getSize() {
        return this.size;
    }
    public void setSize(long size) {
        this.size = size;
    }
    public URL getWebUrl() {
        return this.webUrl;
    }
    public void setWebUrl(URL webUrl) {
        this.webUrl = webUrl;
    }
    public net.tjeerd.onedrive.json.largefile.File getFile() {
        return this.file;
    }
    public void setFile(net.tjeerd.onedrive.json.largefile.File file) {
        this.file = file;
    }
    public FileSystemInfo getFileSystemInfo() {
        return this.fileSystemInfo;
    }
    public void setFileSystemInfo(FileSystemInfo fileSystemInfo) {
        this.fileSystemInfo = fileSystemInfo;
    }

}
