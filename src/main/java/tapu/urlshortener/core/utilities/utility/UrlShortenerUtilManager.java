package tapu.urlshortener.core.utilities.utility;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.lang.*;
import java.io.*;

import org.apache.commons.validator.routines.UrlValidator;
import com.google.common.hash.Hashing;
import org.springframework.web.bind.annotation.PathVariable;


/*
*
* //https://en.wikipedia.org/wiki/Alaca_H%C3%B6y%C3%BCk

        https://en.wikipedia.org/ Ö
        https://en.wikipedia.org/ ö

        https://en.wikipedia.org/  İ
        https://en.wikipedia.org/  ı

        https://en.wikipedia.org/  Ü
        https://en.wikipedia.org/ ü

        https://en.wikipedia.org/ Ç
        https://en.wikipedia.org/ ç

        https://en.wikipedia.org/%C4%9E Ğ
        https://en.wikipedia.org/ ğ

        https://en.wikipedia.org/ Ş
        https://en.wikipedia.org/ ş
* */
@Service
public  class UrlShortenerUtilManager implements UrlShortenerUtilService {

    private static final Hashtable<String, String> turkishChars = new Hashtable<String, String>(){
        {
            put("Ö","%C3%96");
            put("ö","%C3%B6");
            put("İ","%C4%B0");
            put("ı","%C4%B1");
            put("Ü","%C3%9C");
            put("ü","%C3%BC");
            put("Ç","%C3%87");
            put("ç","%C3%A7");
            put("Ğ","%C4%9E");
            put("ğ","%C4%9F");
            put("Ş","%C5%9E");
            put("ş","%C5%9F");
        }
    };

    public String getValidUrl(String url){
        StringBuilder validString = new StringBuilder();
        for(int i=0;i < url.length();i++){
            char c = url.charAt(i);
            if(turkishChars.get(String.valueOf(c)) != null){
                String value = turkishChars.get(String.valueOf(c)).toString();
                validString.append(value);
            }else{
                validString.append(c);
            }
        }
        return validString.toString();
    }


    public String create(String url) {
        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );
//// TODO: 26.06.2021 should check if there is a record in the Table then you dont need to generate just return it  otherwise keep continue
        if (urlValidator.isValid(url.toString())) {
            System.out.println(url+ " is valid url");
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

