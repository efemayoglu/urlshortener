package tapu.urlshortener;

import com.google.common.hash.Hashing;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EncodingTests {


    @Test
    public void shouldEncodeTurkishCharacter(){
        String charSet = "https://en.wikipedia.org/wiki/Alaca_Höyük";

        //        String charSet = "https://en.wikipedia.org/wiki/Alaca_H%C3%B6y%C3%BCk";
        //d085f9ed

        String encoded = Hashing.murmur3_32().hashString(charSet, StandardCharsets.UTF_8).toString();


        assertNotNull(encoded);
        assertTrue(encoded.length() > 0);

    }
}
