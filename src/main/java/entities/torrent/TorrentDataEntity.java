package entities.torrent;

import entities.content.HvssContentObject;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "HVSS_TORRENT_DATA")
public class TorrentDataEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String uid;

    private String torrentFileURL;
    private String torrentName;
    private String torrentFileSavePath;
    private String downloadedContentSavePath;
    private String generalFolderName;

    private Timestamp torrentCreationDate;
    private Timestamp finishDate;
    private Timestamp deletionFromClientDate;

}
