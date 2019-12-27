package services;

import com.turn.ttorrent.client.Client;
import config.ContentConfig;
import config.TorrentConfig;
import dao.content.HvssContentDao;
import dao.content.HvssContentElementDao;
import dao.content.HvssContentObjectDao;
import dto.node.HvssNodeTorrentInfoData;
import dto.web.HvssTorrentInfoData;
import dto.web.TorrentData;
import entities.HvssContentObject;
import entities.content.HvssContent;
import entities.torrent.HvssTorrentDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.*;
import java.util.logging.Logger;

@Component

public class TorrentService <T extends HvssContentObject> {

    private final static Logger LOGGER = Logger.getLogger(TorrentService.class.getName());

    @Autowired
    private ContentConfig contentConfig;

    @Autowired
    private SlaveService<dto.node.commdata.HvssNodeCommDataObject> slaveService;

    @Autowired
    private TorrentConfig torrentConfig;

    @Autowired
    private HvssContentDao hvssContentDao;

    @Autowired
    private HvssContentObjectDao hvssContentObjectDao;

    @Autowired
    private HvssContentElementDao hvssContentElementDao;

    @Autowired
    private FileManagerService fileManagerService;

    //String - TorrentId, HvssTorrentInfoData - torrentData
    Map<String, HvssTorrentInfoData> oIDtorrentInfoDataMap = new HashMap<>();

    public void deleteTorrentFile(String torrentId) {
        slaveService.deleteTorrent(torrentId, oIDtorrentInfoDataMap.get(torrentId).getNodeSlaveData());
        oIDtorrentInfoDataMap.remove(torrentId);
        //TODO Delete torrent from file sys && moce files to archive
    }

    public HvssNodeTorrentInfoData getTorrentData(String oid){
        HvssTorrentInfoData data = oIDtorrentInfoDataMap.get(oid);
        if(data != null){
            if(data.getNodeTorrentInfoData().getClientState() == Client.ClientState.DONE){
                return data.getNodeTorrentInfoData();
            } else {
               return slaveService.updateTorrentInfoDataFromSlave(oid, data.getNodeSlaveData());
            }
        }
        return new HvssNodeTorrentInfoData();
    }

    public List<HvssNodeTorrentInfoData> getAllTorrentDataList(){
        List<HvssNodeTorrentInfoData> returnList = new ArrayList<>();
        oIDtorrentInfoDataMap.entrySet().stream().forEach((e) -> {
            HvssNodeTorrentInfoData updateData = slaveService.updateTorrentInfoDataFromSlave(e.getKey(), e.getValue().getNodeSlaveData());
            e.getValue().setNodeTorrentInfoData(updateData);
            returnList.add(updateData);
        });
        return returnList;
    }

    public void addTorrentFileToContentObject(String oid, TorrentData torrentData) {
        HvssContentObject contentObject = hvssContentObjectDao.getContentObject(oid);
        contentObject.setTorrentData(createContentObjectTorrentData(torrentData));
        HvssTorrentInfoData data = slaveService.addTorrentToSlaveNode(contentObject.getTorrentData());
        data.setHvssContentObject(contentObject);
        oIDtorrentInfoDataMap.put(contentObject.getTorrentData().getUid(), data);
        Class<T> cls = (Class<T>) contentObject.getHvssType().getTypeClass();
        hvssContentObjectDao.merge(cls.cast(contentObject));
    }

    private <T extends HvssContentObject> HvssTorrentDataEntity createContentObjectTorrentData(TorrentData torrentData){
        HvssTorrentDataEntity data = new HvssTorrentDataEntity();
        data.setUid(UUID.randomUUID().toString());
        data.setTorrentFileURL(torrentData.getFileURL());
        data.setGeneralFolderName(torrentData.getOid());
        data.setTorrentName(torrentData.getOid() + ".torrent");
        data.setTorrentFileSavePath(contentConfig.getTorrentFilePath() + "/");
        data.setDownloadedContentSavePath(contentConfig.getStoragePath() + "/" + torrentData.getOid());
        data.setTorrentCreationDate(new Timestamp(System.currentTimeMillis()));
        return data;
    }

    public void downloadStartTorrentFile(Long id) {
        //TODO Delete torrent from bittorrent client
        //TODO Delete torrent from file sys
    }

    public void downloadStopTorrentFile(Long id) {
        //TODO Delete torrent from bittorrent client
        //TODO Delete torrent from file sys
    }

    public void downloadStart() {
        //TODO Delete torrent from bittorrent client
        //TODO Delete torrent from file sys
    }

    public void downloadStop() {
        //TODO Delete torrent from bittorrent client
        //TODO Delete torrent from file sys
    }

    //TODO if finished, index file path to storage
    //TODO if seedFinished move to archive, reindex files

    public void doTorrentMaintenance(){
        List<HvssNodeTorrentInfoData> returnList = getAllTorrentDataList();
        for (HvssNodeTorrentInfoData data: returnList) {
            HvssTorrentInfoData objectData = oIDtorrentInfoDataMap.get(data.getTorrentId());
            if(data.getClientState() == Client.ClientState.DONE && !objectData.isTorrentVerified()){

            }
        }
    }

}