package tapu.urlshortener.business.abstracts;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.dtos.UserWithUrlMapResponse;

import java.util.List;

public interface UrlService {

    DataResult<Url> getUrlByFromLink(String fromLink);
    DataResult<Url> getUrlByToLink(String toLink);

    DataResult<Url> getById(int id);

    Url addUrlOrGet(String toLink);

    List<UserWithUrlMapResponse> getUrlsByCreatedUserId(int userId);
}
