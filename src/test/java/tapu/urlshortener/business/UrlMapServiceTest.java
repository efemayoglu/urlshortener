package tapu.urlshortener.business;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.business.abstracts.UrlService;
import tapu.urlshortener.business.abstracts.UserService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlMapServiceTest {

    private UrlMapService mapService;
    private UrlService urlService;
    private UserService userService;

    @Autowired
    public UrlMapServiceTest(UrlMapService mapService, UrlService urlService, UserService userService){
        this.mapService = mapService;
        this.urlService = urlService;
        this.userService = userService;
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
        final String fromLink = "ca2c5aeb";
        final String expectedValue = "https://www.facebook.com/";
        final String unexpectedValue = "www.google.com1";

        var result = urlService.getUrlByFromLink(fromLink);

        System.out.println(result.getData().getFromLink());
        assertTrue(result.getData().getToLink().equals(expectedValue));
        assertFalse(result.getData().getToLink().equals(unexpectedValue));
    }

    @Test
    void shouldNotAddUrlIntoUser(){
        final int userId = 1;
        final String toLinkId = "https://www.facebook.com";

        //var user = userService.getById(userId);
        //var url = urlService.getById(toLinkId);

        var result = mapService.addUrlIntoUser(userId, toLinkId);

        System.out.println(result.isSuccess());
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldAddUrlIntoUser(){
        final int userId = 1;
        final String toLinkId = "https://www.facebook.com/";

        //var user = userService.getById(userId);
        //var url = urlService.getById(toLinkId);

        var result = mapService.addUrlIntoUser(userId, toLinkId);

        System.out.println(result.isSuccess());
        assertTrue(result.isSuccess());

    }
}
