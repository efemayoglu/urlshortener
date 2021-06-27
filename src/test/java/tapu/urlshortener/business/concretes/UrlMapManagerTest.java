package tapu.urlshortener.business.concretes;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tapu.urlshortener.business.abstracts.UserService;
import tapu.urlshortener.core.utilities.results.*;
import tapu.urlshortener.core.utilities.utility.UrlShortenerUtilService;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.concretes.User;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class UrlMapManagerTest {


    @InjectMocks
    private UrlMapManager mapManager;

    @Mock
    private UrlManager urlService;

    @Mock
    private UserService userManager;

    @Mock
    private UrlShortenerUtilService urlShortenerUtilService;

    @Test
    void addUrlIntoUser() {
        User user = mock(User.class);
        String toLink = "test-to-link";
        int userId = 1;

        when(userManager.getUserById(anyInt())).thenReturn(user);

        var result = mapManager.addUrlIntoUser(userId, toLink);

        assertNotNull(result);
        assertEquals(true, result.isSuccess());
    }

    @Test
    void deleteUrlFromUser() {
        int urlId = 5;
        int userId = 1;

        Url url = new Url();
        url.setId(urlId);
        url.setToLink("test-to-link");
        url.setFromLink("test-from-link");

        User user  = new User();
        user.setId(userId);
        user.setUsername("test-user");
        user.setPassword("test-password");
        user.setUrls(Arrays.asList(url));

        User mockUser = mock(User.class);

        DataResult<Url> dataResultInstance = new SuccessDataResult<>();
        dataResultInstance.setSuccess(true);
        dataResultInstance.setData(url);

        when(userManager.getUserById(anyInt())).thenReturn(user);

        when(urlService.getById(anyInt())).thenReturn(dataResultInstance);

        when(userManager.save(any(User.class))).thenReturn(mockUser);

        var result = mapManager.deleteUrlFromUser(userId, urlId);

        assertEquals(true, result.isSuccess());

    }
}