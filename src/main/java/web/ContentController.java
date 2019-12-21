package web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController("content")
public class ContentController {

    @GetMapping("/buffer/{id}")
    public void bufferContent(@PathVariable("id") Long id){

    }

}
