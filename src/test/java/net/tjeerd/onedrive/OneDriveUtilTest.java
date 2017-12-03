package net.tjeerd.onedrive;

import net.tjeerd.onedrive.core.OneDriveAPI;
import net.tjeerd.onedrive.core.OneDriveUtil;
import net.tjeerd.onedrive.enums.FriendlyNamesEnum;
import net.tjeerd.onedrive.exception.RestException;
import net.tjeerd.onedrive.json.folder.Data;
import net.tjeerd.onedrive.json.folder.Folder;

import java.io.IOException;

public class OneDriveUtilTest {

    private static String CLIENT_ID = "01234567890";
    private static String CLIENT_SECRET = "ABCDEFGHIJklmnopqrstuvwxyz123";
    private static String AUTHORIZATION_CODE = "abcdefg-12345-hijklmn-67890-opqrstvwxyz";

    //Reference to OneDriveAPI
    private static OneDriveAPI myOneDriveAPI;

    public static void main(String[] args) {
        // Making 'login' and getting the reference for client account
        myOneDriveAPI = OneDriveUtil
                .getInstance(CLIENT_ID, CLIENT_SECRET, AUTHORIZATION_CODE).getOneDriveAPI();

        //listing all files
        listFiles();

        //creating a folder
        createFolder("My folder", "Folder for test", "");

    }

    public static void listFiles() {
        try {
            Folder folder = myOneDriveAPI.getMyFilesList(FriendlyNamesEnum.ALL);

            System.out.println("Total folder entries found: " + folder.getData().size());

            for (Data data : folder.getData())
                System.out.println("Entry name: "+ data.getName() + ", type: " + data.getType() + ", id: " + data.getId());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createFolder(String folderName, String folderId, String folderDescription) {
        try {
            myOneDriveAPI.createFolder(folderName, folderId, folderDescription);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RestException e) {
            e.printStackTrace();
        }
    }
}
