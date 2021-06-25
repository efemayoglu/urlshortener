package tapu.urlshortener.businessTests.abstracts;

import tapu.urlshortener.core.utilities.results.Result;
import tapu.urlshortener.entities.concretes.User;

public interface UserService {
    Result add(User user);
}
