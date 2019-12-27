package web;

import config.ContentConfig;
import dto.response.HvssRepsonse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.FileManagerService;

@Controller
@RequestMapping("file")
public class FileManagerController {

    @Autowired
    ContentConfig contentConfig;

    @Autowired
    FileManagerService fileManagerService;

    public ResponseEntity<HvssRepsonse> contentifyNAS(){
        return null;
    }

    public ResponseEntity<HvssRepsonse> contentifyBuffer(){
        return null;
    }

    public ResponseEntity<HvssRepsonse> contentifyStorage(){
        return null;
    }



}
