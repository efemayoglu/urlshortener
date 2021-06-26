package tapu.urlshortener.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateLinkRequest {

    private int userId;
    private String toLink;
}
