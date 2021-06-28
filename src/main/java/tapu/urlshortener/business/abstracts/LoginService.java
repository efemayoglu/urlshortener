package tapu.urlshortener.business.abstracts;

import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.entities.dtos.LoginRequest;
import tapu.urlshortener.entities.dtos.LoginResponse;

public interface LoginService{

    DataResult<LoginResponse> getByUsernameAndPassword(LoginRequest loginRequest);

}
