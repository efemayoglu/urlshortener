package tapu.urlshortener.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tapu.urlshortener.business.abstracts.UserService;
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

    @Override
    public User getById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }

}
