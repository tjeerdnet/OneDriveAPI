package net.tjeerd.onedrive.json.largefile;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.net.URI;
import java.util.List;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UploadSession {
    private URI upload_url;
    private Date expiration_date_time;
    private List<String> next_expected_ranges;

    public URI getUploadUrl() {
        return this.upload_url;
    }

    public void setUploadUrl(URI upload_url) {
        this.upload_url = upload_url;
    }

    public Date getExpirationDateTime() {
        return this.expiration_date_time;
    }

    public void setExpirationDateTime(Date expiration_date_time) {
        this.expiration_date_time = expiration_date_time;
    }

    public List<String> getNextExpectedRanges() {
        return this.next_expected_ranges;
    }

    public void setNextExpectedRanges(List<String> next_expected_ranges) {
        this.next_expected_ranges = next_expected_ranges;
    }
}
