package tapu.urlshortener.business.abstracts;

import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.entities.dtos.UserWithUrlMapResponse;

import java.util.List;

public interface UrlMapService {
    DataResult<List<UserWithUrlMapResponse>> getByCreatedUser(int userId);
}
