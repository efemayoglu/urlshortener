package tapu.urlshortener.business.abstracts;

import tapu.urlshortener.core.utilities.results.Result;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.UserCreateRequest;

public interface UserService {
    Result add(User user);
    User getById(int id);
    User getUserById(int id);
    User save(User user);
    Result save(UserCreateRequest user);
}
