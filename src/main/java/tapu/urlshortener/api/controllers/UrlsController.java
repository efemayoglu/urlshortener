package tapu.urlshortener.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.entities.dtos.UserWithUrlMap;

import java.util.List;

@RestController
@RequestMapping("/api/urls")
public class UrlsController {

    private UrlMapService urlMapService;

    @Autowired
    public UrlsController(UrlMapService urlMapService){
        this.urlMapService = urlMapService;
    }

    @GetMapping("/getByCreatedUser")
    public DataResult<List<UserWithUrlMap>> getByCreatedUser(@RequestParam int userId){
        return this.urlMapService.getByCreatedUser(userId);
    }
    //// TODO: 26.06.2021 add create operation for url 
    //// TODO: 26.06.2021 should not be repeated values for shortener link



}
