package tapu.urlshortener.dataAccess.abstracts;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.concretes.User;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class UrlDaoTest {

    @Autowired
    private UrlDao urlDao;

    @Autowired
    private UserDao userDao;

    @Test
    void itShouldGetUrlByFromLink() {
        String fromLink = UUID.randomUUID().toString();
        String toLink = UUID.randomUUID().toString();
        Url url = new Url();
        url.setFromLink(fromLink);
        url.setToLink(toLink);
        urlDao.save(url);

        var result = urlDao.getUrlByFromLink(fromLink);
        assertNotNull(result);
        assertNotEquals(0, result.getId());
        assertEquals(fromLink, url.getFromLink());
        assertEquals(toLink, url.getToLink());
    }

    @Test
    void itShouldGetUrlByToLink() {
        String fromLink = UUID.randomUUID().toString();
        String toLink = UUID.randomUUID().toString();
        Url url = new Url();
        url.setFromLink(fromLink);
        url.setToLink(toLink);
        urlDao.save(url);

        var result = urlDao.getUrlByFromLink(fromLink);
        assertNotNull(result);
        assertNotEquals(0, result.getId());
        assertEquals(fromLink, url.getFromLink());
        assertEquals(toLink, url.getToLink());
    }

    @Test
    void itShouldGetUrlsByCreatedUserId() {
        String username = UUID.randomUUID().toString();
        String password = UUID.randomUUID().toString();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //add url
        String fromLink = UUID.randomUUID().toString();
        String toLink = UUID.randomUUID().toString();
        Url url = new Url();
        url.setFromLink(fromLink);
        url.setToLink(toLink);

        Url addedUrl = urlDao.save(url);
        assertNotNull(addedUrl);


        user.setUrls(Arrays.asList(url));

        User addedUser = userDao.save(user);
        assertNotNull(addedUser);
        assertNotEquals(0, addedUser.getId());

        var result = urlDao.getUrlsByCreatedUserId(addedUser.getId());
        assertNotNull(result);

        assertTrue(result.size() == 1);

    }
}