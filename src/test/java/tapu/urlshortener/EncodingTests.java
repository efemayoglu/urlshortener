package tapu.urlshortener;

import com.google.common.hash.Hashing;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.core.utilities.utility.UrlShortenerUtilService;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EncodingTests {

    private UrlShortenerUtilService urlShortenerUtilService;

    @Autowired
    public EncodingTests(UrlShortenerUtilService urlShortenerUtilService) {
        this.urlShortenerUtilService = urlShortenerUtilService;
    }

    @Test
    public void shouldEncodeTurkishCharacter(){
        String charSet = "https://en.wikipedia.org/wiki/Alaca_Höyük";

        //        String charSet = "https://en.wikipedia.org/wiki/Alaca_H%C3%B6y%C3%BCk";
        //d085f9ed

        String encoded = Hashing.murmur3_32().hashString(charSet, StandardCharsets.UTF_8).toString();


        assertNotNull(encoded);
        assertTrue(encoded.length() > 0);

    }
    @Test
    public void shouldMatchTwoString(){
        String url1 = urlShortenerUtilService.getUrl("https://en.wikipedia.org/wiki/Alaca_H%C3%B6y%C3%BCk");
        String url2 = urlShortenerUtilService.getUrl("https://en.wikipedia.org/wiki/Alaca_Höyük");

        assertEquals(url1, url2);
    }

    @Test
    public void shouldReturnExpected(){
        String url1 = urlShortenerUtilService.getUrl("https://en.wikipedia.org/wiki/Alaca_H%C3%B6y%C3%BCk");
        String url2 = urlShortenerUtilService.getUrl("https://en.wikipedia.org/wiki/Alaca_Höyük");

        String expected = "d085f9ed";

        String id1 = urlShortenerUtilService.create(url1);
        String id2= urlShortenerUtilService.create(url1);

        assertEquals(id1, id2);
    }
}
