package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.stereotype.Component;

@Configuration
public class ConfigurationReader {

    @Value("${torrent.seedTime}")
    private int seedTime;

    @Value("${application.drives.archiveFolder}")
    private String archiveFolderPath;

    @Value("${application.drives.bufferFolder}")
    private String bufferFolderPath;

    @Value("${application.drives.torrentDownloadFolderPath}")
    private String torrentDownloadFolderPath;

    @Value("${application.drives.torrentFileFolderPath}")
    private String torrentFileFolderPath;

    @Bean
    public TorrentConfig torrentConfig() {

        TorrentConfig torrentConfig = new TorrentConfig();
        torrentConfig.setSeedTime(seedTime);
        return torrentConfig;
    }

    @Bean
    public ContentConfig contentConfig() {

        ContentConfig contentConfig = new ContentConfig();
        contentConfig.setArchivePath(archiveFolderPath);
        contentConfig.setBufferPath(bufferFolderPath);
        contentConfig.setStoragePath(torrentDownloadFolderPath);
        contentConfig.setTorrentFilePath(torrentFileFolderPath);
        return contentConfig;
    }
}
