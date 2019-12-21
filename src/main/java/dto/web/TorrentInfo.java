package dto.web;

import lombok.Data;

import java.util.Date;

@Data
public class TorrentInfo {

    String torrentName;
    int downloadPercentage;
    Date downloadDate;

}
