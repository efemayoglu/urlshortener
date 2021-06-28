package tapu.urlshortener.business;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.business.abstracts.UrlService;
import tapu.urlshortener.business.abstracts.UserService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.core.utilities.results.Result;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.UserWithUrlMapResponse;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlMapServiceIntegrationTest {

    private UrlMapService mapService;
    private UrlService urlService;
    private UserService userService;

    @Autowired
    public UrlMapServiceIntegrationTest(UrlMapService mapService, UrlService urlService, UserService userService){
        this.mapService = mapService;
        this.urlService = urlService;
        this.userService = userService;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void itShouldFindUrlsByUserId(){
        DataResult<List<UserWithUrlMapResponse>> result = mapService.getByCreatedUser(1);
        assertTrue(result.getData().size() > 0);
    }

    @Test
    void itShouldFindUrlByFromLink(){
        String toLink ="https://www."+ UUID.randomUUID()+".com";

        Url addedUrl = urlService.addUrlOrGet(toLink);
        assertNotNull(addedUrl);

        DataResult<Url> result = urlService.getUrlByFromLink(addedUrl.getFromLink());

        assertNotNull(result);
        assertNotNull(result.getData());
        assertTrue(result.isSuccess());
        assertNotEquals(0, result.getData().getId());
    }

    @Test
    void itShouldNotAddTheSameUrlIntoSameUser(){

        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        String toLink ="https://www."+ UUID.randomUUID()+".com";

        Url addedUrl = urlService.addUrlOrGet(toLink);
        assertNotNull(addedUrl);

        user.setUrls(Arrays.asList(addedUrl));

        User addedUser = userService.save(user);
        assertNotNull(addedUrl);

        Result result = mapService.addUrlIntoUser(addedUser.getId(), toLink);
        assertFalse(result.isSuccess());
    }

    @Test
    void itShouldAddUrlIntoUser(){

        User user = new User();
        user.setUsername(UUID.randomUUID().toString());
        user.setPassword(UUID.randomUUID().toString());

        User addedUser = userService.save(user);
        assertNotNull(addedUser);

        final String toLink = "https://www."+UUID.randomUUID()+".com/";

        Result result = mapService.addUrlIntoUser(addedUser.getId(), toLink);
        assertNotNull(result);
        assertTrue(result.isSuccess());

    }
}
