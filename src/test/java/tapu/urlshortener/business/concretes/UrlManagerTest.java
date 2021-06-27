package tapu.urlshortener.business.concretes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;
import tapu.urlshortener.core.utilities.utility.UrlShortenerUtilService;
import tapu.urlshortener.dataAccess.abstracts.UrlDao;
import tapu.urlshortener.entities.concretes.Url;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    @Test
    void addUrlOrGet() {

        String toLink = "https://www.google.com";
        Url urlMock = mock(Url.class);

        when(urlServiceManager.addUrlOrGet(ArgumentMatchers.any(String.class))).thenReturn(urlMock);

        Url result = urlServiceManager.addUrlOrGet(toLink);

        assertNotNull(result);
    }

  /*  @Test
    void addUrlOrGetExists() {
        String toLink = "https://www.google.com";
        Url urlMock = mock(Url.class);

        int expectedId = 57;

     //   when(urlDao.getUrlByToLink(ArgumentMatchers.any(String.class))).thenReturn(urlMock);
        Url result = urlServiceManager.addUrlOrGet(toLink);

        when(urlServiceManager.addUrlOrGet(ArgumentMatchers.any(String.class))).thenReturn(urlMock);



        when(result.getToLink()).thenReturn(toLink);
        when(result.getId()).thenReturn(expectedId);

        assertEquals(result.getToLink(), toLink);
        assertEquals(expectedId, result.getId());
    }*/
    @Test
    void getUrlsByCreatedUserId() {
    }
}