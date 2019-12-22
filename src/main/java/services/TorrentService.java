package services;

import com.turn.ttorrent.client.Client;
import com.turn.ttorrent.client.SharedTorrent;
import config.ContentConfig;
import config.TorrentConfig;
import dao.content.HvssContentDao;
import dao.content.HvssContentElementDao;
import dto.web.TorrentData;
import entities.HvssObject;
import entities.content.HvssContent;
import entities.content.HvssContentElement;
import entities.content.HvssContentObject;
import entities.torrent.TorrentDataEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.HashIdentifierUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Component
public class TorrentService<T> {

    @Autowired
    private ContentConfig contentConfig;

    @Autowired
    private TorrentConfig torrentConfig;

    @Autowired
    private HvssContentDao hvssContentDao;

    @Autowired
    private HvssContentElementDao hvssContentElementDao;

    @Autowired
    private FileManager fileManager;

    @Autowired
    HashIdentifierUtils hashIdentifierUtils;

    Map<String, Client> torrentClients = new HashMap<>();

    Client client;

    public Map<String, Client.ClientState> getTorrentStatuses(){
        Map<String, Client.ClientState> retMap = new HashMap<>();
        torrentClients.forEach((k, v) -> {
            retMap.put(k, v.getState());

        });
        return retMap;
    }

    public void deleteTorrentFile(Long id) {
        //TODO Delete torrent from bittorrent client
        //TODO Delete torrent from file sys

    }

    public void addContentTorrentFile(Long id, TorrentData torrentData) {
        HvssContent content = hvssContentDao.findById(id);
        content.setTorrentData(new TorrentDataEntity());
        content.getTorrentData().setUid(hashIdentifierUtils.createNewHashId());
        content.getTorrentData().setTorrentFileURL(torrentData.getFileURL());
        content.getTorrentData().setGeneralFolderName(content.getTorrentData().getUid());
        content.getTorrentData().setTorrentName(content.getTorrentData().getUid() + ".torrent");
        content.getTorrentData().setTorrentFileSavePath(contentConfig.getTorrentFilePath()+ "/");
        content.getTorrentData().setDownloadedContentSavePath(contentConfig.getStoragePath()  + "/" + content.getTorrentData().getUid());
        content.getTorrentData().setTorrentCreationDate(new Timestamp(System.currentTimeMillis()));
        startTorrent(content);
        hvssContentDao.merge(content);
    }

    public void addContentElementTorrentFile(Long id, TorrentData torrentData) {
        HvssContentElement contentElement = hvssContentElementDao.findById(id);
        contentElement.setTorrentData(new TorrentDataEntity());
        contentElement.getTorrentData().setUid(hashIdentifierUtils.createNewHashId());
        contentElement.getTorrentData().setTorrentFileURL(torrentData.getFileURL());
        contentElement.getTorrentData().setGeneralFolderName(contentElement.getTorrentData().getUid());
        contentElement.getTorrentData().setTorrentName(contentElement.getTorrentData().getUid() + ".torrent");
        contentElement.getTorrentData().setTorrentFileSavePath(contentConfig.getTorrentFilePath() + "/");
        contentElement.getTorrentData().setDownloadedContentSavePath(contentConfig.getStoragePath() + "/" + contentElement.getTorrentData().getUid());
        contentElement.getTorrentData().setTorrentCreationDate(new Timestamp(System.currentTimeMillis()));
        startTorrent(contentElement);
        hvssContentElementDao.merge(contentElement);
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

    private <T extends HvssContentObject> void startTorrent(T hvssContentObject) {

        try (BufferedInputStream in = new BufferedInputStream(new URL(hvssContentObject.getTorrentData().getTorrentFileURL()).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(hvssContentObject.getTorrentData().getTorrentFileSavePath() + hvssContentObject.getTorrentData().getTorrentName())) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            if(!new File(hvssContentObject.getTorrentData().getDownloadedContentSavePath()).exists()){
                new File(hvssContentObject.getTorrentData().getDownloadedContentSavePath()).mkdirs();
            }
            Client client = new Client(
                    InetAddress.getLocalHost(),
                    SharedTorrent.fromFile(
                            new File(hvssContentObject.getTorrentData().getTorrentFileSavePath() + hvssContentObject.getTorrentData().getTorrentName()),
                            new File(hvssContentObject.getTorrentData().getDownloadedContentSavePath())));
            client.setMaxDownloadRate(8000.0);
            client.setMaxUploadRate(8000.0);
            hvssContentObject.getTorrentData().setTorrentCreationDate(new Timestamp(System.currentTimeMillis()));
            client.share(torrentConfig.getSeedTime());
            torrentClients.put(hvssContentObject.getUid(), client);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}