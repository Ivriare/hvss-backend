package entities.content;

import dto.web.TorrentData;
import entities.HvssObject;
import entities.torrent.TorrentDataEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class HvssContentObject extends HvssObject {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "TORRENT_DATA_ID", referencedColumnName = "id")
    private TorrentDataEntity torrentData;
}
