package config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
public class ContentConfig {

    private String archivePath;
    private String bufferPath;
    private String storagePath;
    private String torrentFilePath;
    private String userPicturePath;
    private String contentPicturePath;
    private String snapshotPicturePath;
    private String videoFormatRegex;
    private String subtitleFormatRegex;
    private List<String> subtitleExtensions;

}
