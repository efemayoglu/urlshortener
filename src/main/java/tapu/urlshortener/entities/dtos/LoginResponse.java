package tapu.urlshortener.entities.dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import tapu.urlshortener.entities.concretes.Url;

import java.util.HashSet;
import java.util.List;


@Data
@AllArgsConstructor
public class LoginResponse {

    private int id;

    private String username;

    private  List<Url> urls;

}
