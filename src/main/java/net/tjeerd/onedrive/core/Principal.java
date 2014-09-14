package net.tjeerd.onedrive.core;

import net.tjeerd.onedrive.json.OAuth20Token;

public class Principal {
    private String clientId;
    private String clientSecret;
    private String authorizationCode;
    private OAuth20Token oAuth20Token;

    public Principal(String clientId, String clientSecret, String authorizationCode) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorizationCode = authorizationCode;
        this.oAuth20Token = new OAuth20Token();
    }

    public Principal(String clientId, String clientSecret, String authorizationCode, String refreshToken) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.authorizationCode = authorizationCode;
        this.oAuth20Token = new OAuth20Token();
        this.oAuth20Token.setRefresh_token(refreshToken);

    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAuthorizationCode() {
        return authorizationCode;
    }

    public void setAuthorizationCode(String authorizationCode) {
        this.authorizationCode = authorizationCode;
    }

    public OAuth20Token getoAuth20Token() {
        return oAuth20Token;
    }

    public void setoAuth20Token(OAuth20Token oAuth20Token) {
        this.oAuth20Token = oAuth20Token;
    }
}
