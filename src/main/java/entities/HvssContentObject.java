package entities;

import entities.file.HvssFileLocationTypeEnum;
import entities.torrent.HvssTorrentDataEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@MappedSuperclass
public class HvssContentObject extends HvssObject {

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "TORRENT_DATA_ID", referencedColumnName = "id")
    private HvssTorrentDataEntity torrentData;
    private Boolean torrentVerified;

}
