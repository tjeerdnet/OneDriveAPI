package net.tjeerd.onedrive.core;

import java.util.Properties;

/**
 *
 *  This class takes off client the need of creating
 * unusual object to them like Properties of Principal.
 *
 *  This implementation provides a singleton to client
 * and it needs only to specify its 'login' parameters,
 * such as ID and secret.
 */
public class OneDriveUtil {

    private static OneDriveUtil INSTANCE = null;

    private OneDrive oneDriveAPI;

    /**
     * Default constructor without parameters.
     */
    private OneDriveUtil() {}

    /**
     * This one already takes information need to
     * have an OneDriveAPI instance.
     *
     * @param clientId
     * @param clientSecret
     * @param authorizationCode
     * @param refreshToken
     */
    private OneDriveUtil(String clientId, String clientSecret,
                           String authorizationCode, String refreshToken) {

        Properties properties = new Properties();

        oneDriveAPI = new OneDrive(new Principal(properties.getProperty(clientId),
                properties.getProperty(clientSecret),
                properties.getProperty(authorizationCode),
                properties.getProperty(refreshToken)));

        oneDriveAPI.initAccessTokenByRefreshTokenAndClientId();
    }

    public static OneDriveUtil getInstance(String clientId, String clientSecret,
                                           String authorizationCode, String refreshToken) {
        if(INSTANCE == null)
            INSTANCE = new OneDriveUtil(clientId, clientSecret, authorizationCode, refreshToken);
        return INSTANCE;
    }

    /**
     *  It provides to client access to OneDriveAPI instance.
     *   With it, client can use its features like creating
     * new folder, file, update...
     *
     * @return OneDriveAPI instance
     */
    public OneDrive getOneDriveAPI() {
        return oneDriveAPI;
    }

}
