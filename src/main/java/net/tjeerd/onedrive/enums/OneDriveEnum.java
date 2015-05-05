package net.tjeerd.onedrive.enums;

public enum OneDriveEnum {
    OAUTH20_AUTHORIZE_URL("https://login.live.com/oauth20_authorize.srf"),
    OAUTH20_TOKEN_URL("https://login.live.com/oauth20_token.srf"),
    OAUTH20_DESKTOP_REDIRECT_URL("https://login.live.com/oauth20_desktop.srf"),
    API_URL("https://apis.live.net/v5.0/"),
    API_URL_v1("https://api.onedrive.com/v1.0/"),
    GRANT_TYPE_AUTHORIZATION_CODE("authorization_code"),
    GRANT_TYPE_REFRESH_TOKEN("refresh_token");

    private String value;
    
    /**
     * Construct a new OneDriveAPI enumeration.
     * 
     * @param value value of the key
     */
    private OneDriveEnum(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public String toString() {
        return this.value;
    }
    
}
