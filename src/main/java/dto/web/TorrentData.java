package dto.web;

import entities.content.HvssContentTypeEnum;
import lombok.Data;

@Data
public class TorrentData {
    String fileURL;
    String contentName;
}
