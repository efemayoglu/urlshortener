package tapu.urlshortener.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.business.abstracts.UrlService;
import tapu.urlshortener.core.utilities.utility.UrlShortenerUtilService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/r")

public class RedirectController {


    private UrlMapService mapService;
    private UrlShortenerUtilService urlShortenerUtilService;
    private UrlService urlService;

    public RedirectController(UrlMapService mapService, UrlShortenerUtilService urlShortenerUtilService, UrlService urlService) {
        this.mapService = mapService;
        this.urlShortenerUtilService = urlShortenerUtilService;
        this.urlService = urlService;
    }

    @GetMapping("/")
    public String getUrl(@RequestParam String fromLink){
        //// TODO: 26.06.2021 Get DB or Redis
       // var toLink = urlShortenerUtilService.getUrl(fromLink);
        //System.out.println(toLink);
    //    return toLink;

      var url = urlService.getUrlByFromLink(fromLink);

      return url.getData().getToLink();
        //return urlService.getUrlByToLink(toLink).getData().getFromLink()

    }
    @GetMapping("/{fromLink}")
    public void redirectToFullUrl(HttpServletResponse response, @PathVariable String fromLink) {
        try {
            var url = urlService.getUrlByFromLink(fromLink);
            response.sendRedirect(url.getData().getToLink());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url not found", e);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not redirect to the url", e);
        }
    }


    
}
