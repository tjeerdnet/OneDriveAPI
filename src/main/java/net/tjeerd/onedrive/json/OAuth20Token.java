package net.tjeerd.onedrive.json;

public class OAuth20Token extends Error {
    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String scope;
    private String token_type;
    private String user_id;

    public String getAccess_token(){
        return this.access_token;
    }
    public void setAccess_token(String access_token){
        this.access_token = access_token;
    }
    public int getExpires_in(){
        return this.expires_in;
    }
    public void setExpires_in(int expires_in){
        this.expires_in = expires_in;
    }
    public String getRefresh_token(){
        return this.refresh_token;
    }
    public void setRefresh_token(String refresh_token){
        this.refresh_token = refresh_token;
    }
    public String getScope(){
        return this.scope;
    }
    public void setScope(String scope){
        this.scope = scope;
    }
    public String getToken_type(){
        return this.token_type;
    }
    public void setToken_type(String token_type){
        this.token_type = token_type;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
}

