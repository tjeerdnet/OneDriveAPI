package net.tjeerd.onedrive;

import net.tjeerd.onedrive.core.OneDrive;
import net.tjeerd.onedrive.core.Principal;
import net.tjeerd.onedrive.enums.FriendlyNamesEnum;
import net.tjeerd.onedrive.json.User;
import net.tjeerd.onedrive.json.folder.Data;
import net.tjeerd.onedrive.json.folder.Folder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class OneDriveTest {
    private static final String ONEDRIVE_PROPERTIESFILE = "onedrive.properties";
    private static final String ONEDRIVE_TESTFILE = "onedrivetestfile.docx";
    private static OneDrive oneDriveAPI;
    private static Principal principal;

    @BeforeClass
    public static void readPropertiesAndInitializeOneDriveAPI() throws IOException {
        Properties properties = new Properties();

        InputStream inputStream = OneDriveTest.class.getClassLoader().getResourceAsStream(ONEDRIVE_PROPERTIESFILE);
        properties.load(inputStream);

        if (inputStream == null) {
            throw new FileNotFoundException("OneDriveAPI properties file '" + ONEDRIVE_PROPERTIESFILE+ "' not found in the classpath");
        }
        principal = new Principal(properties.getProperty("clientid"), properties.getProperty("clientsecret"),
                                  properties.getProperty("authorizationcode"), properties.getProperty("refreshtoken"));

        oneDriveAPI = new OneDrive(principal,true);
        oneDriveAPI.initAccessTokenByRefreshTokenAndClientId();
    }

    @Test
    public void getMyFilesListTest() throws Exception {
        Folder folder = oneDriveAPI.getMyFilesList(FriendlyNamesEnum.ALL);
        assertNotNull(folder);

        System.out.println("Total folder entries found: " + folder.getData().size());

        for (Data data : folder.getData()) {
            System.out.println("Entry name: "+ data.getName() + ", type: " + data.getType() + ", id: " + data.getId());
        }
    }

    @Test
    public void getUserTest() throws Exception {
        User user = oneDriveAPI.getUser();
        assertNotNull(user);

        System.out.println("Username: " + user.getName());
    }

    @Test
    public void uploadDownloadRenameAndDeleteFileTest() throws Exception {
        java.io.File oneDriveTestfile = new java.io.File(getClass().getClassLoader().getResource(ONEDRIVE_TESTFILE).getFile());
        net.tjeerd.onedrive.json.folder.File oneDriveFile = oneDriveAPI.uploadFile(oneDriveTestfile, "");
        assertNotNull(oneDriveFile);

        oneDriveFile.setName("renamedfile.docx");
        oneDriveFile.setDescription("Renamed description");

        oneDriveAPI.updateFile(oneDriveFile);

        net.tjeerd.onedrive.json.folder.File oneDriveFileRenamed = oneDriveAPI.getFile(oneDriveFile.getId());
        assertEquals(oneDriveFileRenamed.getName(),"renamedfile.docx");
        assertEquals(oneDriveFileRenamed.getDescription(), "Renamed description");

        oneDriveAPI.deleteFile(oneDriveFile);
    }
    
    @Test
    public void createFolderTest() throws Exception {
        net.tjeerd.onedrive.json.folder.Folder oneDriveFolder = oneDriveAPI.createFolder("Testfolder","Step1InitToken description","");
        assertNotNull(oneDriveFolder);

        oneDriveFolder.setName("TestfolderRenamed");
        Folder oneDriveFolderUpdated = oneDriveAPI.updateFolder(oneDriveFolder);
        assertNotNull(oneDriveFolderUpdated);

        oneDriveAPI.deleteFolder(oneDriveFolderUpdated.getId());
    }
    
    @Test
    public void uploadLargeFileTest() throws Exception {
        java.io.File oneDriveTestfile = new java.io.File(getClass().getClassLoader().getResource(ONEDRIVE_TESTFILE).getFile());
//        net.tjeerd.onedrive.json.folder.File oneDriveFile = oneDriveAPI.uploadFile(oneDriveTestfile, "");
//        assertNotNull(oneDriveFile);

        oneDriveAPI.uploadLargeFile(oneDriveTestfile, "");
    	
    }
}
