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
    void addUrlOrGet() {
        String toLink = "https://www."+ UUID.randomUUID()+".com";
        Url result = urlServiceManager.addUrlOrGet(toLink);

        assertEquals(result.getToLink(), toLink);
        assertTrue(result.getId() > 0);
    }

}
