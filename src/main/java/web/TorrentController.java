package web;

import com.turn.ttorrent.client.Client;
import dto.web.TorrentData;
import dto.web.TorrentStatusData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.TorrentService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("torrent")
public class TorrentController {

    @Autowired
    TorrentService torrentService;

    @GetMapping(value = "/getAllInfo")
    public ResponseEntity<Map<String, Client.ClientState>> getTorrentInfo(){
        return new ResponseEntity<>(torrentService.getTorrentStatuses(), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteTorrentFile(@PathVariable Long id){
        torrentService.deleteTorrentFile(id);

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/content/add/{id}")
    public ResponseEntity<String> addContentTorrentFile(@PathVariable Long id, @RequestBody TorrentData torrentData){
        torrentService.addContentTorrentFile(id, torrentData);

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/contentelement/add/{id}")
    public ResponseEntity<String> addContentElementTorrentFile(@PathVariable Long id, @RequestBody TorrentData torrentData){
        torrentService.addContentElementTorrentFile(id, torrentData);

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/download/start/{id}")
    public ResponseEntity<String> downloadStartTorrentFile(@PathVariable Long id){
        torrentService.downloadStartTorrentFile(id);

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/download/stop/{id}")
    public ResponseEntity<String> downloadStopTorrentFile(@PathVariable Long id){
        torrentService.downloadStopTorrentFile(id);

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/download/start")
    public ResponseEntity<String> downloadStart(){
        torrentService.downloadStart();

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/download/stop")
    public ResponseEntity<String> downloadStop(){
        torrentService.downloadStop();

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }



}
