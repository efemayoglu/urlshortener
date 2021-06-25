package tapu.urlshortener.business.abstracts;

import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.entities.concretes.User;

public interface LoginService{

    DataResult<User> getByUsernameAndPassword(String username, String password);

}
