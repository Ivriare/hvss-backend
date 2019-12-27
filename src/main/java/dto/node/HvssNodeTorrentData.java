package dto.node;

import dto.node.commdata.HvssNodeCommDataObject;
import lombok.Data;

@Data
public class HvssNodeTorrentData extends HvssNodeCommDataObject {
    private String torrentOid;
    private String torrentUrl;
    private String torrentStorageFolder;
    private String torrentName;


}
