package net.tjeerd.onedrive.core;

/**
 *
 *  This class takes off client the need of creating
 * unusual object to them like Principal.
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
     */
    private OneDriveUtil(String clientId, String clientSecret,
                           String authorizationCode) {

        oneDriveAPI = new OneDrive(new Principal(clientId, clientSecret, authorizationCode));
        oneDriveAPI.initAccessTokenByRefreshTokenAndClientId();
    }

    public static OneDriveUtil getInstance(String clientId, String clientSecret,
                                           String authorizationCode) {
        if(INSTANCE == null)
            INSTANCE = new OneDriveUtil(clientId, clientSecret, authorizationCode);
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
