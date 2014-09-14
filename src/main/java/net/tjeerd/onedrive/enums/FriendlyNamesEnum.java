package net.tjeerd.onedrive.enums;

public enum FriendlyNamesEnum {
    ALL(""),
    DOCUMENTS("my_documents"),
    PUBLIC("public_documents"),
    PICTURES("my_photos");

    private String value;
    
    /**
     * Construct a new OneDriveAPI enumeration.
     * 
     * @param value value of the key
     */
    private FriendlyNamesEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String toString() {
        return this.value;
    }
    
}
