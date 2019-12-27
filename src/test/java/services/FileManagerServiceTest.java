package services;

import config.ContentConfig;
import config.TorrentConfig;
import dto.filemanager.treegen.TreeGenNode;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;


@ActiveProfiles("test")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {ContentConfig.class, FileManagerService.class})
class FileManagerServiceTest {

    @SpyBean
    ContentConfig contentConfig;

    @MockBean
    TorrentConfig torrentConfig;

    @Autowired
    FileManagerService fileManagerService;

    @Before
    public void setup() {
        ReflectionTestUtils.setField(contentConfig, "videoFormatRegex", ".+(\\.avchd|\\.avi|\\.avi|\\.mp4|\\.flv|\\.mkv|\\.mov|\\.mp4|\\.webm|\\.wmv)$");
        ReflectionTestUtils.setField(contentConfig, "subtitleFormatRegex", "([a-zA-Z0-9\\s_\\.\\-\\(\\):])+(.ass|.str)$");
        ReflectionTestUtils.setField(contentConfig, "subtitleExtensions", Arrays.asList(".str", ".ass"));
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void genTree() {
        contentConfig.setVideoFormatRegex(".+(\\.avchd|\\.avi|\\.avi|\\.mp4|\\.flv|\\.mkv|\\.mov|\\.mp4|\\.webm|\\.wmv)$");
        contentConfig.setSubtitleFormatRegex("([a-zA-Z0-9\\s_\\.\\-\\(\\):])+(.ass|.str)$");
        contentConfig.setSubtitleExtensions(Arrays.asList(".str", ".ass"));
        TreeGenNode tree =  fileManagerService.genTree("G:\\anime\\Absolute Duo (BD_1280x720)");
        System.out.println();

    }
}