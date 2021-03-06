package tapu.urlshortener.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.business.abstracts.UrlService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.core.utilities.results.Result;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.dtos.CreateLinkRequest;
import tapu.urlshortener.entities.dtos.DeleteLinkRequest;
import tapu.urlshortener.entities.dtos.GetUrlsRequest;
import tapu.urlshortener.entities.dtos.UserWithUrlMapResponse;

import java.util.List;

@RestController
@RequestMapping("/api/urls")
public class UrlsController {

    private UrlMapService urlMapService;
    private UrlService urlService;

    @Autowired
    public UrlsController(UrlMapService urlMapService, UrlService urlService){
        this.urlMapService = urlMapService;
        this.urlService = urlService;
    }

    @PostMapping("/getByCreatedUser")
    public DataResult<List<UserWithUrlMapResponse>> getByCreatedUser(@RequestBody GetUrlsRequest request){
        return this.urlMapService.getByCreatedUser(request.getUserId());
    }
    //// TODO: 26.06.2021 add create operation for url 
    //// TODO: 26.06.2021 should not be repeated values for shortener link

    @PostMapping("/add")
    public Result addUrl(@RequestBody CreateLinkRequest request){
        return this.urlMapService.addUrlIntoUser(request.getUserId(), request.getToLink());
    }

    @DeleteMapping("/delete")
    public Result deleteUrl(@RequestBody DeleteLinkRequest request){
        return this.urlMapService.deleteUrlFromUser(request.getUserId(), request.getUrlId());
    }



}
