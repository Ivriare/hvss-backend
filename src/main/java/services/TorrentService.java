package services;

import com.turn.ttorrent.client.Client;
import dto.web.AddTorrentInfo;
import entities.content.HvssContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TorrentService {

    @Autowired
    FileManager fileManager;

    @Autowired
    Client client;

    public void deleteTorrentFile(Long id){
        //TODO Delete torrent from bittorrent client
        //TODO Delete torrent from file sys

    }

    public void addTorrent(AddTorrentInfo addTorrentInfo){
        //TODO download torrent file from path
        //TODO Save torrent file to file system
        //TODO save content and torrent file to database

        HvssContent hvssContent = new HvssContent();
        hvssContent.setContentName(addTorrentInfo.getContentName());
        hvssContent.setContentTypeId(addTorrentInfo.getContentType());
        if(!addTorrentInfo.getFilePath().isEmpty()) {
            hvssContent.setCoverPicturePath(addTorrentInfo.getPictureLink());
        } else if (false){

        }
    }

    public void downloadStartTorrentFile(Long id){
        //TODO Delete torrent from bittorrent client
        //TODO Delete torrent from file sys

    }

    public void downloadStopTorrentFile(Long id){
        //TODO Delete torrent from bittorrent client
        //TODO Delete torrent from file sys

    }

    public void downloadStart(){
        //TODO Delete torrent from bittorrent client
        //TODO Delete torrent from file sys

    }

    public void downloadStop(){
        //TODO Delete torrent from bittorrent client
        //TODO Delete torrent from file sys

    }

}
