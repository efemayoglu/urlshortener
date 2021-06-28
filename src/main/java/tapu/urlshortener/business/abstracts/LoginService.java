package tapu.urlshortener.business.abstracts;

import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.LoginRequest;

public interface LoginService{

    DataResult<User> getByUsernameAndPassword(LoginRequest loginRequest);

}
