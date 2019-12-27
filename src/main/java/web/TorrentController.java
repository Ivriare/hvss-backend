package web;

import dto.node.HvssNodeTorrentInfoData;
import dto.web.TorrentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import services.TorrentService;

import java.util.List;
import java.util.Map;

@Controller
@RestController
@RequestMapping("torrent")
public class TorrentController {

    @Autowired
    TorrentService torrentService;

    @GetMapping(value = "/status")
    public ResponseEntity<List<HvssNodeTorrentInfoData>> getAllTorrentInfo(){
        return new ResponseEntity<>(torrentService.getAllTorrentDataList(), HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/status/{id}")
    public ResponseEntity<HvssNodeTorrentInfoData> getTorrentInfo(@PathVariable String oid){
        return new ResponseEntity<>(torrentService.getTorrentData(oid), HttpStatus.ACCEPTED);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<String> deleteTorrentFile(@PathVariable String id){
        torrentService.deleteTorrentFile(id);

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    //ContentObject OID
    @PostMapping(value = "/content/add/{id}")
    public ResponseEntity<String> addContentTorrentFile(@PathVariable String oid, @RequestBody TorrentData torrentData){
        torrentService.addTorrentFileToContentObject(oid, torrentData);

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
