package tapu.urlshortener;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.core.utilities.utility.UrlShortenerUtil;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UrlShortenerUtilityTests {

    public UrlShortenerUtilityTests(){

    }

    @Test
    public void shouldCreateShortLink(){
        final String link = "https://www.google.com";
        final String expectedValue = "50328aa4";

        var utility = new UrlShortenerUtil();

        String result =  utility.create(link);

        assertEquals(result, expectedValue);

      // String toLink = UrlShortener.idToShortURL("www.google.com");
    }
    @Test
    public void shouldGetLink(){
        final String expectedValue = "https://www.google.com";
        final String link = "50328aa4";

        var utility = new UrlShortenerUtil();

        String result =  utility.getUrl(link);

        assertEquals(result, expectedValue);

        // String toLink = UrlShortener.idToShortURL("www.google.com");
    }
}
