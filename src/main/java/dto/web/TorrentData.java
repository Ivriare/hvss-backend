package dto.web;

import entities.torrent.HvssTorrentTypeEnum;
import lombok.Data;

@Data
public class TorrentData {
    String fileURL;
    String contentName;
    String oid;
    HvssTorrentTypeEnum hvssTorrentTypeEnum;
}
