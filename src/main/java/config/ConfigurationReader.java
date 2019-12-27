package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import java.util.List;

@Configuration
public class ConfigurationReader {

    @Value("${application.drives.archiveFolder}")
    private String archiveFolderPath;

    @Value("${application.drives.bufferFolder}")
    private String bufferFolderPath;

    @Value("${application.drives.torrentDownloadFolderPath}")
    private String torrentDownloadFolderPath;

    @Value("${application.drives.torrentFileFolderPath}")
    private String torrentFileFolderPath;

    @Value("${application.drives.userPic}")
    private String userPicturePath;

    @Value("${application.drives.contentPic}")
    private String contentPicturePath;

    @Value("${application.drives.snapshotPic}")
    private String snapshotPicturePath;

    @Value("${application.content.videoFormatRegex}")
    private String videoFormatRegex;

    @Value("${application.content.subtitleFormatRegex}")
    private String subtitleFormatRegex;

    @Value("#{'${application.content.subtitleExtensions}'.split(';')}")
    private List<String> subtitleExtensionList;

    @Bean
    public TorrentConfig torrentConfig() {

        TorrentConfig torrentConfig = new TorrentConfig();
        return torrentConfig;
    }

    @Bean
    public ContentConfig contentConfig() {

        ContentConfig contentConfig = new ContentConfig();
        contentConfig.setArchivePath(archiveFolderPath);
        contentConfig.setBufferPath(bufferFolderPath);
        contentConfig.setStoragePath(torrentDownloadFolderPath);
        contentConfig.setTorrentFilePath(torrentFileFolderPath);
        contentConfig.setUserPicturePath(userPicturePath);
        contentConfig.setSnapshotPicturePath(snapshotPicturePath);
        contentConfig.setContentPicturePath(contentPicturePath);
        contentConfig.setVideoFormatRegex(videoFormatRegex);
        contentConfig.setSubtitleFormatRegex(subtitleFormatRegex);
        contentConfig.setSubtitleExtensions(subtitleExtensionList);
        return contentConfig;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
