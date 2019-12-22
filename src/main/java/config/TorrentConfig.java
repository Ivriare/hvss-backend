package config;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Data
public class TorrentConfig {

    private int seedTime;
    private String torrentFileFolder;
}
