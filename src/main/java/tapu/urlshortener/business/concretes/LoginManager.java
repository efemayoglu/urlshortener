package tapu.urlshortener.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tapu.urlshortener.business.abstracts.LoginService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.core.utilities.results.ErrorDataResult;
import tapu.urlshortener.core.utilities.results.SuccessDataResult;
import tapu.urlshortener.dataAccess.abstracts.UserDao;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.LoginRequest;
import tapu.urlshortener.entities.dtos.LoginResponse;

@Service
public class LoginManager implements LoginService {

    private UserDao userDao;

    @Autowired
    public LoginManager(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public DataResult<LoginResponse> getByUsernameAndPassword(LoginRequest loginRequest) {
        User result = userDao.getByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        if(result == null)
            return new ErrorDataResult<>("username or password is wrong");


        return new SuccessDataResult<>(new LoginResponse(result.getId(), result.getUsername(), result.getUrls()));
    }
}
