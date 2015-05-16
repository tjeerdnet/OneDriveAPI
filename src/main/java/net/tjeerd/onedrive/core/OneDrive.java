package net.tjeerd.onedrive.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import net.tjeerd.onedrive.enums.FriendlyNamesEnum;
import net.tjeerd.onedrive.enums.OneDriveEnum;
import net.tjeerd.onedrive.exception.RestException;
import net.tjeerd.onedrive.json.Quota;
import net.tjeerd.onedrive.json.SharedLink;
import net.tjeerd.onedrive.json.User;
import net.tjeerd.onedrive.json.folder.Folder;
import net.tjeerd.onedrive.json.largefile.Accepted;
import net.tjeerd.onedrive.json.largefile.CreatedLargeFile;
import net.tjeerd.onedrive.json.largefile.UploadSession;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class OneDrive implements OneDriveAPI {
    private static final Logger logger = LoggerFactory.getLogger(OneDrive.class);
    private OneDriveCore oneDriveCore;
    private Principal principal;
    private Client client;

    private static final String API_PATH_ME = "me";
    private static final String API_PATH_ME_SKYDRIVE_QUOTA = "me/skydrive/quota";
    private static final String API_PATH_ME_SKYDRIVE = "me/skydrive";
    private static final String API_PATH_ME_SKYDRIVE_FILES = "me/skydrive/files";
    private static final String API_PATH_FILES = "files";
    private static final String API_PATH_CONTENT = "content?download=true";
    private static final String API_PATH_SHARED_EDIT_LINK = "shared_edit_link";
    private static final String API_PATH_SHARED_READ_LINK = "shared_read_link";

    private static final String API_PATH_LARGE_FILE_ROOT = "drive/root:/%s:/upload.createSession";
    private static final String API_PATH_LARGE_FILE_FOLDER = "drive/items/%s:/%s/upload.createSession";
    /**
     * 10MB = 10 * 1024 * 1024
     */
    private static final int LARGE_FILE_FRAGMENT_MAX_SIZE = 10 * 1024 * 1024;
    private static final HashMap<String, String> LARGE_FILE_POST_REQUEST_BODY = new HashMap<String, String>() {
        private static final long serialVersionUID = -5800640500118562565L;
        {
            put("@name.conflictBehavior", "rename");
        }
    };

    /**
     * Construct the OneDriveAPI API with the principal as argument.
     *
     * @param principal
     *            principal containing tokens, client identification, client
     *            secret etc.
     */
    public OneDrive(Principal principal) {
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        defaultClientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        this.client = Client.create(defaultClientConfig);
        this.principal = principal;
        oneDriveCore = new OneDriveCore(client, principal);
    }

    /**
     * Construct the OneDriveAPI API with the principal as argument and
     * optionally enable debugging.
     *
     * @param principal
     *            principal containing token, client identification, client
     *            secret etc.
     * @param debug
     *            true to enable debugging, otherwise false
     */
    public OneDrive(Principal principal, boolean debug) {
        DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
        defaultClientConfig.getClasses().add(JacksonJsonProvider.class);
        // defaultClientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,
        // Boolean.TRUE);
        this.client = Client.create(defaultClientConfig);

        if (debug) {
            client.addFilter(new LoggingFilter());
        }

        this.principal = principal;
        oneDriveCore = new OneDriveCore(client, principal);
    }

    /**
     * {@inheritDoc}
     */
    public boolean initTokenByPrincipal() {
        if (hasPrincipalClientSecretAndClientIdAndAuthorizationCode()) {
            logger.info("Client-id, client secret and authorization code found, trying to initialize tokens");
            this.principal.setoAuth20Token(oneDriveCore.getTokenByPrincipal());
            return true;
        } else {
            logger.error("Cannot initialize tokens, missing client-id, client secret or authorization code");
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public boolean initAccessTokenByRefreshTokenAndClientId() {
        if (hasPrincipalRefreshTokenAndClientId()) {
            this.principal.getoAuth20Token().setAccess_token(oneDriveCore.getAccessTokenByRefreshTokenAndClientId().getAccess_token());
            return true;
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    public Principal getPrincipal() {
        return this.principal;
    }

    /**
     * {@inheritDoc}
     */
    public Client getClient() {
        return this.client;
    }

    /**
     * Return whether the principal has a client identification and secret and
     * authorization code set.
     *
     * @return true if principal has a client identification, secret and
     *         authorization code set, otherwise false
     */
    private boolean hasPrincipalClientSecretAndClientIdAndAuthorizationCode() {
        return (StringUtils.isNotEmpty(principal.getClientId()) && StringUtils.isNotEmpty(principal.getClientSecret()) && StringUtils.isNotEmpty(principal.getAuthorizationCode()));
    }

    /**
     * Return whether the principal has a refresh token and client
     * identification set.
     *
     * @return true if principal has a refresh token and client identification
     *         set, otherwise false
     */
    private boolean hasPrincipalRefreshTokenAndClientId() {
        return (StringUtils.isNotEmpty(principal.getoAuth20Token().getRefresh_token()) && StringUtils.isNotEmpty(principal.getClientId()));
    }

    /**
     * {@inheritDoc}
     */
    public User getUser() throws Exception {
        return (User) oneDriveCore.doGetAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, API_PATH_ME, new User());
    }

    /**
     * {@inheritDoc}
     */
    public Quota getQuota() throws Exception {
        return (Quota) oneDriveCore.doGetAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, API_PATH_ME_SKYDRIVE_QUOTA, new Quota());
    }

    /**
     * {@inheritDoc}
     */
    public Folder getMyFilesList(FriendlyNamesEnum friendlyNamesEnum) throws Exception {
        String apiPath = API_PATH_ME_SKYDRIVE;

        if (friendlyNamesEnum.equals(FriendlyNamesEnum.ALL)) {
            apiPath += "/" + API_PATH_FILES;
        } else {
            apiPath += friendlyNamesEnum.toString() + "/" + API_PATH_FILES;
        }

        return (Folder) oneDriveCore.doGetAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, apiPath, new Folder());
    }

    /**
     * {@inheritDoc}
     */
    public Folder getFileList(String folderId) throws Exception {
        return (Folder) oneDriveCore.doGetAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, folderId + "/" + API_PATH_FILES, new Folder());
    }

    /**
     * {@inheritDoc}
     */
    public void downloadFile(net.tjeerd.onedrive.json.folder.File oneDriveFile, String destinationFilePath) {
        ClientResponse clientResponse = oneDriveCore.doGetAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_OCTET_STREAM, oneDriveFile.getId() + "/" + API_PATH_CONTENT);
        java.io.File destinationFile = new java.io.File(destinationFilePath);

        if (clientResponse.getStatus() == ClientResponse.Status.FOUND.getStatusCode()) {
            /*
             * The response is a redirect location, so do a new call to get the
             * real download location
             */
            try {
                clientResponse = oneDriveCore.doGetAPI(clientResponse.getLocation().toString(), MediaType.APPLICATION_OCTET_STREAM);
                java.io.File resultFile = clientResponse.getEntity(java.io.File.class);
                org.apache.commons.io.FileUtils.moveFile(resultFile, destinationFile);
                logger.info("OneDriveAPI file '" + oneDriveFile.getName() + "' saved to '" + destinationFilePath + "' (" + destinationFile.length() + " bytes)");
            } catch (Exception exception) {
                logger.error("Could not download from redirect location " + clientResponse.getLocation());
                exception.printStackTrace();
            }
        } else if (clientResponse.getStatus() == ClientResponse.Status.OK.getStatusCode()) {
            /*
             * The response contains the location, so save the response to a
             * file
             */
            try {
                java.io.File resultFile = clientResponse.getEntity(java.io.File.class);
                org.apache.commons.io.FileUtils.moveFile(resultFile, destinationFile);
                logger.info("OneDriveAPI file '" + oneDriveFile.getName() + "' saved to '" + destinationFilePath + "' (" + destinationFile.length() + " bytes)");
            } catch (Exception e) {
                logger.error("Cannot download or write file with identifier '" + oneDriveFile.getId() + "' to destination '" + destinationFilePath + "'");
                e.printStackTrace();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public net.tjeerd.onedrive.json.folder.File getFile(String fileId) throws Exception {
        return (net.tjeerd.onedrive.json.folder.File) oneDriveCore.doGetAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, fileId, new net.tjeerd.onedrive.json.folder.File());
    }

    /**
     * {@inheritDoc}
     */
    public net.tjeerd.onedrive.json.folder.Folder getFolder(String folderId) throws Exception {
        return (net.tjeerd.onedrive.json.folder.Folder) oneDriveCore.doGetAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, folderId, new net.tjeerd.onedrive.json.folder.Folder());
    }

    /**
     * {@inheritDoc}
     */
    public void deleteFile(net.tjeerd.onedrive.json.folder.File oneDriveFile) throws RestException {
        oneDriveCore.doDeleteAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, oneDriveFile.getId());
    }

    /**
     * {@inheritDoc}
     */
    public net.tjeerd.onedrive.json.folder.File updateFile(net.tjeerd.onedrive.json.folder.File oneDriveFile) throws RestException, IOException {
        return (net.tjeerd.onedrive.json.folder.File) oneDriveCore.doPutAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, oneDriveFile.getId(), oneDriveFile);
    }

    /**
     * {@inheritDoc}
     */
    public net.tjeerd.onedrive.json.folder.Folder updateFolder(net.tjeerd.onedrive.json.folder.Folder oneDriveFolder) throws RestException, IOException {
        return (Folder) oneDriveCore.doPutAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, oneDriveFolder.getId(), oneDriveFolder);
    }

    /**
     * {@inheritDoc}
     */
    public net.tjeerd.onedrive.json.folder.Folder createFolder(String name, String description, String locationFolderId) throws RestException, IOException {
        String apiPath;
        net.tjeerd.onedrive.json.folder.Folder oneDriveFolder = new Folder();
        oneDriveFolder.setName(name);
        oneDriveFolder.setDescription(description);

        if (StringUtils.isEmpty(locationFolderId)) {
            apiPath = API_PATH_ME_SKYDRIVE;
        } else {
            apiPath = locationFolderId;
        }

        return (Folder) oneDriveCore.doPostAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, apiPath, oneDriveFolder);
    }

    /**
     * {@inheritDoc}
     */
    public void deleteFolder(String folderId) throws RestException {
        oneDriveCore.doDeleteAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, folderId);
    }

    /**
     * {@inheritDoc}
     */
    public net.tjeerd.onedrive.json.folder.File uploadFile(File file, String folderId) {
        net.tjeerd.onedrive.json.folder.File oneDriveFile = new net.tjeerd.onedrive.json.folder.File();
        MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
        ObjectMapper objectMapper = new ObjectMapper();
        String apiPath = "";

        if (folderId.isEmpty()) {
            apiPath = API_PATH_ME_SKYDRIVE_FILES + "/" + file.getName();
        } else {
            apiPath = folderId + "/" + API_PATH_FILES + "/" + file.getName();
        }

        queryParams.add(OneDriveCore.API_PARAM_ACCESS_TOKEN, principal.getoAuth20Token().getAccess_token());
        WebResource webResource = client.resource(OneDriveEnum.API_URL.toString() + apiPath);

        try {
            ClientResponse clientResponse = webResource.queryParams(queryParams).type(MediaType.APPLICATION_JSON).put(ClientResponse.class, fileToByteArray(file));
            oneDriveFile = objectMapper.readValue(clientResponse.getEntity(String.class).toString(), net.tjeerd.onedrive.json.folder.File.class);
        } catch (Exception e) {
            logger.error("Cannot upload file '" + file.getAbsolutePath() + "' to OneDriveAPI");
            e.printStackTrace();
        }

        return oneDriveFile;
    }

    /**
     * {@inheritDoc}
     */
    public net.tjeerd.onedrive.json.largefile.CreatedLargeFile uploadLargeFile(File file, String folderId) throws Exception {
        
        net.tjeerd.onedrive.json.largefile.CreatedLargeFile createdFile = null;
        MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();
        UploadSession uploadSession = new UploadSession();
        ObjectMapper objectMapper = new ObjectMapper();
        FileInputStream fileInputStream = new FileInputStream(file);
        WebResource webResource = null;
        ClientResponse clientResponse = null;
        String apiPath = null;
        byte[] fileChunkBuffer = new byte[LARGE_FILE_FRAGMENT_MAX_SIZE];
        byte[] fileChunk;
        int readBytes = 0;
        long uploadBytesCount = 0;

        if (folderId.isEmpty()) {
            apiPath = String.format(API_PATH_LARGE_FILE_ROOT, file.getName());
        } else {
            apiPath = String.format(API_PATH_LARGE_FILE_FOLDER, folderId, file.getName());
        }

        try {
            // Begin large file upload session
            queryParams.add(OneDriveCore.API_PARAM_ACCESS_TOKEN, principal.getoAuth20Token().getAccess_token());
            webResource = client.resource(OneDriveEnum.API_URL_ONEDRIVE.toString() + apiPath);

            clientResponse = webResource.queryParams(queryParams).type(MediaType.APPLICATION_JSON).post(ClientResponse.class, objectMapper.writeValueAsString(LARGE_FILE_POST_REQUEST_BODY));

            if (clientResponse.getStatusInfo().getFamily() != javax.ws.rs.core.Response.Status.Family.SUCCESSFUL) {
                throw new net.tjeerd.onedrive.exception.RestException();
            }
            uploadSession = objectMapper.readValue(clientResponse.getEntity(String.class).toString(), UploadSession.class);

            // Upload file fragments that size is LARGE_FILE_FRAGMENT_MAX_SIZE
            // bytes but last fragment will be less than the constant.
            webResource = client.resource(uploadSession.getUploadUrl());
            while (0 < (readBytes = fileInputStream.read(fileChunkBuffer, 0, LARGE_FILE_FRAGMENT_MAX_SIZE)) && clientResponse.getStatus() != 201) {

                // When the last fragment, limit byte arrays size to read bytes.
                if(readBytes != LARGE_FILE_FRAGMENT_MAX_SIZE) {
                    fileChunk = Arrays.copyOf(fileChunkBuffer, readBytes);
                } else {
                    fileChunk = fileChunkBuffer;
                }

                clientResponse = webResource.queryParams(queryParams).type(MediaType.APPLICATION_JSON).header("Content-Length", readBytes)
                        .header("Content-Range", String.format("bytes %d-%d/%d", uploadBytesCount, uploadBytesCount + readBytes - 1, file.length())).put(ClientResponse.class, fileChunk);

                // HTTP 201, 202, 203 etc means successful
                if (clientResponse.getStatusInfo().getFamily() != javax.ws.rs.core.Response.Status.Family.SUCCESSFUL) {
                    throw new net.tjeerd.onedrive.exception.RestException();
                } else if(clientResponse.getStatus() == 202){
                    Accepted accepted = objectMapper.readValue(clientResponse.getEntity(String.class), Accepted.class);
                }

                // total uploaded byte size is needed for accessing API.
                uploadBytesCount += readBytes;
            }

            // Parse responsed json string to get created file information.
            createdFile = objectMapper.readValue(clientResponse.getEntity(String.class), CreatedLargeFile.class);
        } catch (Exception e) {
            logger.error("Cannot upload file '" + file.getAbsolutePath() + "' to OneDriveAPI");
            e.printStackTrace();
            
            // Cancel the upload session by requesting "DELETE" method.
            clientResponse = webResource.queryParams(queryParams).type(MediaType.APPLICATION_JSON).delete(ClientResponse.class);
        } finally {
            if (fileInputStream != null) {
                fileInputStream.close();
            }
            if (clientResponse != null) {
                clientResponse.close();
            }
        }

        return createdFile;
    }

    /**
     * Convert file content to a byte array.
     *
     * @param file
     *            file object to convert to a byte array
     * @return byte array holding the file content
     */
    private byte[] fileToByteArray(java.io.File file) {
        byte[] byteArrayFile = new byte[(int) file.length()];

        try {
            FileInputStream fileInputStream = null;
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(byteArrayFile);
            fileInputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return byteArrayFile;
    }

    /**
     * {@inheritDoc}
     */
    public SharedLink getSharedLink(String fileId, boolean isEditable) throws Exception {
        String apiPath;

        if (isEditable) {
            apiPath = fileId + "/" + API_PATH_SHARED_EDIT_LINK;
        } else {
            apiPath = fileId + "/" + API_PATH_SHARED_READ_LINK;
        }

        return (SharedLink) oneDriveCore.doGetAPI(new MultivaluedMapImpl(), MediaType.APPLICATION_JSON, apiPath, new SharedLink());
    }
}
