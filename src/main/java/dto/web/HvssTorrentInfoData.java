package dto.web;

import dto.node.HvssNodeSlaveData;
import dto.node.HvssNodeTorrentInfoData;
import dto.node.commdata.HvssNodeCommDataObject;
import entities.HvssContentObject;
import entities.torrent.HvssTorrentDataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class HvssTorrentInfoData <T extends HvssContentObject> {
    HvssNodeSlaveData nodeSlaveData;
    HvssNodeTorrentInfoData nodeTorrentInfoData;
    T hvssContentObject;

    public HvssTorrentInfoData(HvssNodeSlaveData nodeSlaveData, HvssNodeTorrentInfoData nodeTorrentInfoData){
        this.nodeSlaveData = nodeSlaveData;
        this.nodeTorrentInfoData = nodeTorrentInfoData;
    }

    public HvssTorrentInfoData(HvssNodeSlaveData nodeSlaveData, HvssNodeTorrentInfoData nodeTorrentInfoData, T contentObject) {
        this.nodeSlaveData = nodeSlaveData;
        this.nodeTorrentInfoData = nodeTorrentInfoData;
        this.hvssContentObject = contentObject;
    }

    public void updateTorrentData(HvssNodeTorrentInfoData torrentInfoData){
        nodeTorrentInfoData.setTorrentId(torrentInfoData.getTorrentId());
        nodeTorrentInfoData.setClientState(torrentInfoData.getClientState());
        nodeTorrentInfoData.setCompletedPieces(torrentInfoData.getCompletedPieces());
        nodeTorrentInfoData.setTotalPieces(torrentInfoData.getTotalPieces());
        nodeTorrentInfoData.setCompletionPercent(torrentInfoData.getCompletionPercent());
    }

    public void verfyTorrent(){
        hvssContentObject.setTorrentVerified(Boolean.TRUE);
        nodeTorrentInfoData.setTorrentVerified(Boolean.TRUE);
    }

    public Boolean isTorrentVerified(){
        return hvssContentObject.getTorrentVerified() && nodeTorrentInfoData.getTorrentVerified();
    }
}
