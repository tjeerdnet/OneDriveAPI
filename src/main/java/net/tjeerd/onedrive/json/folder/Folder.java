package net.tjeerd.onedrive.json.folder;

import java.util.List;

public class Folder extends Data {
    private List<Data> data;
    
    public List<Data> getData() {
        return this.data;
    }
    
    public void setData(List<Data> data) {
        this.data = data;
    }
}
