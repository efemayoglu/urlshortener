package tapu.urlshortener.business.abstracts;

import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.core.utilities.results.Result;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.UserWithUrlMapResponse;

import java.util.List;

public interface UrlMapService {
    DataResult<List<UserWithUrlMapResponse>> getByCreatedUser(int userId);

    //Result addUrlIntoUser(User user, Url url);
    Result addUrlIntoUser(int userId, String toLink);

    Result deleteUrlFromUser(int userId, int urlId);
}
