package web;

import dto.web.ContentData;
import dto.web.ContentGroupData;
import dto.web.TorrentData;
import entities.content.HvssContent;
import entities.content.HvssContentGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import services.ContentService;

import java.util.List;

@Controller
@RestController
@RequestMapping("content")
public class ContentController {

    @Autowired
    ContentService contentService;

    @GetMapping(value = "/buffer/{id}")
    public void bufferContent(@PathVariable("id") Long id){

    }

    @PostMapping(value = "/contentgroup/create")
    public ResponseEntity<Object> addContentGroup(@RequestBody ContentGroupData contentGroupData){
        contentService.addNewContentGroup(contentGroupData);
        return new ResponseEntity<>("Content Group created!", HttpStatus.ACCEPTED);
    }

    @GetMapping(value = "/contentgrouplist")
    public List<HvssContentGroup> getContentGroupList(){
        return contentService.getContentGroupList();
    }

    @GetMapping(value = "/contentgroup/{id}")
    public HvssContentGroup getContentGroup(@PathVariable Long id){
        return contentService.getContentGroup(id);
    }

    @GetMapping(value = "/contentgroup/{id}/content")
    public List<HvssContent> getContentGroupContentList(@PathVariable Long id){
        return contentService.getContentListByGroupId(id);
    }

    @PostMapping("/content/{id}/create")
    public void addContentToContentGroup(@PathVariable Long id, @RequestBody ContentData contentData){
        contentService.addContentToContentGroup(id, contentData);
    }

    @PostMapping("/content/{id}/update/picture")
    public ResponseEntity<Object> uploadPictureToContent(@PathVariable Long id, @RequestParam("file") MultipartFile picture){
        return new ResponseEntity<>("File was", HttpStatus.ACCEPTED);
    }

}
