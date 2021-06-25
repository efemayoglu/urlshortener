package tapu.urlshortener.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tapu.urlshortener.business.abstracts.LoginService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.core.utilities.results.ErrorDataResult;
import tapu.urlshortener.core.utilities.results.SuccessDataResult;
import tapu.urlshortener.dataAccess.abstracts.UserDao;
import tapu.urlshortener.entities.concretes.User;

@Service
public class LoginManager implements LoginService {

    private UserDao userDao;

    @Autowired
    public LoginManager(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public DataResult<User> getByUsernameAndPassword(String username, String password) {
        var result = userDao.getByUsernameAndPassword(username, password);
        if(result == null)
            return new ErrorDataResult<User>("username or password are wrong");
        return new SuccessDataResult<>(result);
    }
}
