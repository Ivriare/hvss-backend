package services;

import config.ContentConfig;
import config.TorrentConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileManager {

    @Autowired
    TorrentConfig torrentConfig;

    @Autowired
    ContentConfig contentConfig;

    public void saveTorrentFile(File file){
        //file.getName()
    }

}
