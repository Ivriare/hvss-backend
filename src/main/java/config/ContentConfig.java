package config;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
public class ContentConfig {

    private String archivePath;
    private String bufferPath;
    private String storagePath;
    private String torrentFilePath;

}
