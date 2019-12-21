package web;

import dto.web.AddTorrentInfo;
import dto.web.TorrentInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import services.TorrentService;

import java.util.Collections;
import java.util.List;

@Controller
@RestController("torrent")
public class TorrentController {

    @Autowired
    TorrentService torrentService;

    @GetMapping("/getAllInfo")
    public ResponseEntity<List<TorrentInfo>> getTorrentInfo(){
        return new ResponseEntity<>(Collections.emptyList(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<String> deleteTorrentFile(@PathVariable Long id){
        torrentService.deleteTorrentFile(id);

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping("/add")
    public ResponseEntity<String> addTorrentFile(@RequestBody AddTorrentInfo addTorrentInfo){
        torrentService.addTorrent(addTorrentInfo);

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping("/download/start/{id}")
    public ResponseEntity<String> downloadStartTorrentFile(@PathVariable Long id){
        torrentService.downloadStartTorrentFile(id);

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping("/download/stop/{id}")
    public ResponseEntity<String> downloadStopTorrentFile(@PathVariable Long id){
        torrentService.downloadStopTorrentFile(id);

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping("/download/start")
    public ResponseEntity<String> downloadStart(){
        torrentService.downloadStart();

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }

    @GetMapping("/download/stop")
    public ResponseEntity<String> downloadStop(){
        torrentService.downloadStop();

        return new ResponseEntity<>("", HttpStatus.ACCEPTED);
    }



}
