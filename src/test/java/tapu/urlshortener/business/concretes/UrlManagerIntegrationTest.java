package tapu.urlshortener.business.concretes;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.entities.concretes.Url;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@SpringBootTest
public class UrlManagerIntegrationTest {


    @Autowired
    private UrlManager urlServiceManager;


    @Test
    void whenCalledWithValidLink_itShouldBeVerified() {
        String toLink = "https://www."+ UUID.randomUUID()+".com";
        assertNotNull(toLink);

        Url result = urlServiceManager.addUrlOrGet(toLink);

        assertNotNull(result);
        assertEquals(toLink, result.getToLink());
        assertTrue(result.getId() > 0);

        Url result2 = urlServiceManager.addUrlOrGet(toLink);
        assertNotNull(result2);

        assertEquals(result.getFromLink(), result2.getFromLink());
        assertEquals(result.getToLink(), result2.getToLink());
        assertEquals(result.getId(), result.getId());
    }

}
