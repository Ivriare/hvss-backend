package dto.node;

import com.turn.ttorrent.client.Client;
import dto.node.commdata.HvssNodeCommDataObject;
import lombok.Data;

@Data
public class HvssNodeTorrentInfoData extends HvssNodeCommDataObject {

    String torrentId;
    Client.ClientState clientState;
    int completedPieces;
    int totalPieces;
    String completionPercent;
    Boolean torrentVerified;


}
