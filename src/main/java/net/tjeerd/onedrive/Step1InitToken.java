package net.tjeerd.onedrive;

import net.tjeerd.onedrive.core.OneDrive;
import net.tjeerd.onedrive.core.Principal;


public class Step1InitToken {
    private static OneDrive oneDriveAPI;
    private static boolean DEBUG = true;
    private static String CLIENT_ID = "01234567890"; // see OneDrive Development Dashboard
    private static String CLIENT_SECRET = "ABCDEFGHIJklmnopqrstuvwxyz123"; // see OneDrive Development Dashboard
    private static String AUTHORIZATION_CODE = "abcdefg-12345-hijklmn-67890-opqrstvwxyz"; // see description below

    /**
     * This program requires the following values which are defined at the top of the class:
     *
     * CLIENT_ID - unique identification for your client/software/app (see OneDrive Developers Dashboard)
     * CLIENT_SECRET - unique secret for your client, also see the OneDrive Developers Dashboard
     * AUTHORIZATION_CODE - unique authorization code you must generate by calling the following URL which
     * returns an authorization code (which is in the URL):
     *
     * https://login.live.com/oauth20_authorize.srf?
     * client_id=01234567890&
     * scope=wl.signin%20wl.basic%20wl.offline_access%20wl.skydrive_update%20wl.skydrive&
     * response_type=code&
     * redirect_uri=https://login.live.com/oauth20_desktop.srf
     *
     * In theory you should have to run this program only once to get the refresh token. The refresh token
     * can be reused every time to get a new access token . The access token is required to make OneDrive API calls.
     *
     * When done, see the OneDriveTest unit test class to see a few examples of OneDrive API calls. Be sure that
     * all properties (onedrive.properties) are defined in the test resources folder.
     *
     * @param args none used
     * @throws Exception
     */
    public static void main(String args[]) throws Exception {
        Principal principal = new Principal(CLIENT_ID, CLIENT_SECRET, AUTHORIZATION_CODE);

        oneDriveAPI = new OneDrive(principal, DEBUG);
        oneDriveAPI.initTokenByPrincipal();

        System.out.println("Refresh token: " + principal.getoAuth20Token().getRefresh_token());
    }
}
