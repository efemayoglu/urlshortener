package tapu.urlshortener.businessTests.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tapu.urlshortener.businessTests.abstracts.UserService;
import tapu.urlshortener.core.utilities.results.Result;
import tapu.urlshortener.core.utilities.results.SuccessDataResult;
import tapu.urlshortener.dataAccess.abstracts.UserDao;
import tapu.urlshortener.entities.concretes.User;

@Service
public class UserManager implements UserService {

    private UserDao userDao;

    @Autowired
    public UserManager(UserDao userDao){
        this.userDao = userDao;
    }


    @Override
    public Result add(User user) {
        return new SuccessDataResult<>(userDao.save(user));
    }

}