package tapu.urlshortener.business.concretes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import tapu.urlshortener.core.utilities.utility.UrlShortenerUtilService;
import tapu.urlshortener.dataAccess.abstracts.UrlDao;
import tapu.urlshortener.entities.concretes.Url;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UrlManagerTest {

    @InjectMocks
    private UrlManager urlServiceManager;

    @Mock
    private UrlDao urlDao;

    @Mock
    private UrlShortenerUtilService urlShortenerUtilService;


    @Test
    void getByCreatedUser() {


    }

    @Test
    void getUrlByFromLink() {
    }

    @Test
    void getUrlByToLink() {
    }

    @Test
    void getById() {

    }
    /*Url url = new Url();
         url.setToLink("https://www.google.com");
         url.setFromLink("");

         User user1 = new User();
         user1.setUsername("test-user-1");
         user1.setPassword("123456");

         User user2 = new User();
         user2.setUsername("test-user-2");
         user2.setPassword("12345");

         HashSet<User> users = new HashSet<>();
         users.add(user1);
         users.add(user2);

         url.setCreatedUser(users);*/
    @Test
    void addUrlOrGet() {

        String toLink = "https://www.google.com";
        Url urlMock = mock(Url.class);

        when(urlServiceManager.addUrlOrGet(ArgumentMatchers.any(String.class))).thenReturn(urlMock);

        Url result = urlServiceManager.addUrlOrGet(toLink);

        when(result.getToLink()).thenReturn(toLink);

        assertEquals(result.getToLink(), toLink);
    }

    @Test
    void getUrlsByCreatedUserId() {
    }
}