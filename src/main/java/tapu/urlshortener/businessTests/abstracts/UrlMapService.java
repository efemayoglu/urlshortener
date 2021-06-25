package tapu.urlshortener.business.abstracts;

import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.UserWithUrlMap;

import java.util.List;

public interface UrlMapService {
    DataResult<List<UserWithUrlMap>> getByCreatedUser(int userId);

}
