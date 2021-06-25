package tapu.urlshortener.business;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tapu.urlshortener.business.abstracts.UrlMapService;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UrlMapServiceTest {

    private UrlMapService mapService;

    @Autowired
    public UrlMapServiceTest(UrlMapService mapService){
        this.mapService = mapService;
    }

    @Test
    void contextLoads() {
    }

    @Test
    void shouldFindUrlsByUserId(){
        var result = mapService.getByCreatedUser(1);

       assertTrue(result.getData().size() > 0);
    }

}
