package dto.web;

import entities.content.HvssContentTypeEnum;
import lombok.Data;

@Data
public class AddTorrentInfo {

    String filePath;
    HvssContentTypeEnum contentType;
    String contentName;
    String pictureLink;
    byte[] picture;
    String desc;

}
