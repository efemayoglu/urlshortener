package tapu.urlshortener.businessTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.businessTests.abstracts.UrlMapService;
import tapu.urlshortener.businessTests.abstracts.UrlService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlMapServiceTest {

    private UrlMapService mapService;
    private UrlService urlService;

    @Autowired
    public UrlMapServiceTest(UrlMapService mapService, UrlService urlService){
        this.mapService = mapService;
        this.urlService = urlService;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void shouldFindUrlsByUserId(){
        var result = mapService.getByCreatedUser(1);
        assertTrue(result.getData().size() > 0);
    }

    @Test
    void shouldFindUrlByFromLink(){
        final String fromLink = "www.mylink.com/abc";
        final String expectedValue = "www.google.com";
        final String unexpectedValue = "www.google.com1";

        var result = urlService.getUrlByFromLink(fromLink);

        System.out.println(result.getData().getFromLink());
        assertTrue(result.getData().getToLink().equals(expectedValue));
        assertFalse(result.getData().getToLink().equals(unexpectedValue));
    }

}