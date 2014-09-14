package net.tjeerd.onedrive.json;

import java.sql.Timestamp;

public class Principal {
    private String    clientId;
    private String    clientSecret;
    private String    authorizationCode;
    private String    access_token;
    private String    refresh_token;
    private int       expires_in;
    private Timestamp accessTokenStart;
    private String    scope;
    private String    user_id;
    
    /**
     * The principal requires to have several credentials, the following is an example of how these credentials MAY look:<br>
     * <ul>
     * <li>client identification: 000000001A111111</li>
     * <li>client secret: aAAA1A1AaaAaAa-11aAaa11aAAAAAaa1</li>
     * <li>authorization code: 1111a111-1111-111a-a1aa-a11111aa1111</li>
     * </ul>
     * 
     * @param clientId client identification
     * @param clientSecret client secret
     * @param authorizationCode authorization code
     */
    public Principal(String clientId, String clientSecret, String authorizationCode) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorizationCode = authorizationCode;
    }
    
    /**
     * The principal requires to have several credentials, the following is an example of how these credentials MAY look:<br>
     * <ul>
     * <li>client identification: 000000001A111111</li>
     * <li>client secret: aAAA1A1AaaAaAa-11aAaa11aAAAAAaa1</li>
     * <li>authorization code: 1111a111-1111-111a-a1aa-a11111aa1111</li>
     * <li>refresh token: AaA1Aa1AAAAAA... (200+ characters)</li>
     * </ul>
     * 
     * @param clientId client identification
     * @param clientSecret client secret
     * @param authorizationCode authorization code
     * @param refreshToken refresh token
     */
    public Principal(String clientId, String clientSecret, String authorizationCode, String refreshToken) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorizationCode = authorizationCode;
        this.refresh_token = refreshToken;
    }
    
    public String getClientId() {
        return clientId;
    }
    
    /**
     * The client identification can be managed from the OneDriveAPI management Dashboard and stays permanent (unless you ask for a new client identification).
     * 
     * @param clientId client identification
     */
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
    
    public String getClientSecret() {
        return clientSecret;
    }
    
    /**
     * The client secret can be managed from the OneDriveAPI management Dashboard and stays permanent (unles you ask for a new client secret).
     * 
     * @param clientSecret client secret
     */
    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    
    public String getAuthorizationCode() {
        return authorizationCode;
    }
    
    /**
     * The authorization code is returned the first time a user accepts the login via the browser (browser is the only way to get the authorization code).
     * 
     * @param authorizationCode
     */
    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }
    
    public String getAccess_Token() {
        return access_token;
    }
    
    /**
     * The access token changes every 3600 seconds (hour).
     * 
     * @param access_token access token
     */
    public void setAccess_Token(String access_token) {
        this.access_token = access_token;
    }
    
    public String getRefresh_Token() {
        return refresh_token;
    }
    
    /**
     * The refresh token changes every 6 months.
     * 
     * @param refresh_token refresh token
     */
    public void setRefresh_Token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
    
    public String getScope() {
        return scope;
    }
    
    /**
     * Set the scope for this principal.
     * 
     * @param scope
     */
    public void setScope(String scope) {
        this.scope = scope;
    }
    
    public int getExpires_In() {
        return expires_in;
    }
    
    /**
     * Total amount of seconds before the access token expires.
     * 
     * @param expires_in total amount of seconds before the token expires
     */
    public void setExpires_In(int expires_in) {
        this.expires_in = expires_in;
    }
    
    public Timestamp getAccessTokenStart() {
        return accessTokenStart;
    }
    
    /**
     * Set the timestamp when the access token was retrieved.
     * 
     * @param accessTokenStart timestamp when the access token was retrieved
     */
    public void setAccessTokenStart(Timestamp accessTokenStart) {
        this.accessTokenStart = accessTokenStart;
    }
    
    public String getUser_Id() {
        return user_id;
    }
    
    public void setUser_Id(String user_id) {
        this.user_id = user_id;
    }
    
}
