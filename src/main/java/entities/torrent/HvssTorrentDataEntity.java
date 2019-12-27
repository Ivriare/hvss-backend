package entities.torrent;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "HVSS_TORRENT_DATA")
public class HvssTorrentDataEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String uid; //TODO change uid to oid as it refers to objectID not user id.... -_-

    private String torrentFileURL;
    private String torrentName;
    private String torrentFileSavePath;
    private String downloadedContentSavePath;
    private String generalFolderName;

    private Timestamp torrentCreationDate;
    private Timestamp finishDate;
    private Timestamp deletionFromClientDate;

}
