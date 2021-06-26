package tapu.urlshortener;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.core.utilities.utility.UrlShortenerUtilManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UrlShortenerUtilityTestsManager {

    public UrlShortenerUtilityTestsManager(){

    }

    @Test
    public void shouldCreateShortLink(){
        final String link = "https://www.google.com";
        final String expectedValue = "50328aa4";

        var utility = new UrlShortenerUtilManager();

        String result =  utility.create(link);

        assertEquals(expectedValue, result);

    }
    @Test
    public void shouldGetLink(){
        final String expectedValue = "https://www.google.com";
        final String link = "50328aa4";

        var utility = new UrlShortenerUtilManager();

        String result =  utility.getUrl(link);

        assertEquals(expectedValue, result);

    }

    @Test
    public void shouldCreateWithTurkishCharacters(){
        final String expectedValue = "d085f9ed";
        final String url = "https://en.wikipedia.org/wiki/Alaca_H%C3%B6y%C3%BCk";

        var utility = new UrlShortenerUtilManager();

        String result =  utility.create(url);

        assertEquals(expectedValue,result);
    }

   /* @Test
    public void shouldGetWithTurkishCharacters(){
        final String expectedValue = "https://en.wikipedia.org/wiki/Alaca_H%C3%B6y%C3%BCk";
        final String id = "d085f9ed";

        var utility = new UrlShortenerUtil();

        String result =  utility.getUrl(id);

        assertEquals(expectedValue,result);
    }*/
}
