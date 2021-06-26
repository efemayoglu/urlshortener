package tapu.urlshortener.entities.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteLinkRequest {

    private int userId;
    private int urlId;
}
