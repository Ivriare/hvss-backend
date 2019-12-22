package dto.web;

import com.turn.ttorrent.client.Client;
import lombok.Data;

import java.util.Date;

@Data
public class TorrentStatusData {

    String torrentName;
    Client.ClientState torrentStatus;
    Date downloadDate;

}
