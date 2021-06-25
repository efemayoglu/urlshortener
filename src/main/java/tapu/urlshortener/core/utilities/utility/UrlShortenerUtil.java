package tapu.urlshortener.core.utilities.utility;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.lang.*;
import java.io.*;

import org.apache.commons.validator.routines.UrlValidator;
import com.google.common.hash.Hashing;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public  class UrlShortenerUtil {

    public String create(String url) {
        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );
//// TODO: 26.06.2021 should check if there is a record in the Table then you dont need to generate just return it  otherwise keep continue
        if (urlValidator.isValid(url)) {
            String id = Hashing.murmur3_32().hashString(url, StandardCharsets.UTF_8).toString();
            System.out.println("URL Id generated: "+ id);
            //redisTemplate.opsForValue().set(id, url);
            return id;
        }

        throw new RuntimeException("URL Invalid: " + url);
    }
    //// TODO: 26.06.2021 Don't throw it Wrap with ErrorResult  
    public String getUrl(String id) {

        String url = "https://www.google.com";
//        String url = redisTemplate.opsForValue().get(id);
        System.out.println("URL Retrieved: " + url);

        if (url == null) {
            throw new RuntimeException("There is no shorter URL for : " + id);
        }
        return url;
    }
}

