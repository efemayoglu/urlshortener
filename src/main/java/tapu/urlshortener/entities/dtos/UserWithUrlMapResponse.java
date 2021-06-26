package tapu.urlshortener.entities.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWithUrlMapDto {

    private int userId;
    private int urlId;
    private String username;
    private String fromLink;
    private String toLink;


}
