package tapu.urlshortener.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.business.abstracts.UserService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.UserWithUrlMap;

import java.util.List;

@RestController
@RequestMapping("/api/urls")
public class UrlController {

    private UrlMapService urlMapService;

    @Autowired
    public UrlController(UrlMapService urlMapService){
        this.urlMapService = urlMapService;
    }

    @GetMapping("/getByCreatedUser")
    public DataResult<List<UserWithUrlMap>> getByCreatedUser(@RequestParam int userId){
        return this.urlMapService.getByCreatedUser(userId);
    }




}
