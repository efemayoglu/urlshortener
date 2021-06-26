package tapu.urlshortener.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("urlRetrieved")

public class UrlController {
    
    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id){
        //// TODO: 26.06.2021 Get DB or Redis
        String url = "";

        System.out.println("url retrieved:"+url);

        //// TODO: 26.06.2021 handle this throw
        if (url == null)
            throw new RuntimeException("there is no shorter url for:"+id);

        return url;
    }
    
}
