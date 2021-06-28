package tapu.urlshortener.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.business.abstracts.UrlService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.core.utilities.utility.UrlShortenerUtilService;
import tapu.urlshortener.entities.concretes.Url;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
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


    @GetMapping("/{fromLink}")
    public void redirectToFullUrl(HttpServletResponse response, @PathVariable String fromLink) {
        try {
            DataResult<Url> url = urlService.getUrlByFromLink(fromLink);
            response.sendRedirect(url.getData().getToLink());
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Url not found", e);
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not redirect to the url", e);
        }
    }


    
}
