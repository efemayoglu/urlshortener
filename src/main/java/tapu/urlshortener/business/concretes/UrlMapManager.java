package tapu.urlshortener.business.concretes;


import org.springframework.stereotype.Service;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.core.utilities.results.Result;
import tapu.urlshortener.core.utilities.results.SuccessDataResult;
import tapu.urlshortener.core.utilities.results.SuccessResult;
import tapu.urlshortener.dataAccess.abstracts.UrlDao;
import tapu.urlshortener.dataAccess.abstracts.UserDao;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.dtos.UserWithUrlMapResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UrlMapManager implements UrlMapService {

    private UrlDao urlDao;
    private UserDao userDao;

    public UrlMapManager(UrlDao urlDao, UserDao userDao) {
        this.urlDao = urlDao;
        this.userDao = userDao;
    }

    @Override
    public DataResult<List<UserWithUrlMapResponse>> getByCreatedUser(int userId) {
        return new SuccessDataResult<>(urlDao.getUrlsByCreatedUserId(userId));
    }

    @Override
    public Result addUrlInUser(int userId, int urlId) {

        var user = userDao.getUserById(userId);
        var url = urlDao.getUrlById(urlId);

        var urls = urlDao.getUrlsByCreatedUserId(userId);

        Set<Url> newUrls = new HashSet<>();
        for (UserWithUrlMapResponse u : urls){
            var newUrl = new Url();
            newUrl.setToLink(u.getToLink());
            newUrl.setFromLink(u.getFromLink());
            newUrls.add(newUrl);
        }

        var newUrl = new Url();

        newUrl.setToLink(url.getToLink());
        newUrl.setFromLink(url.getFromLink());
        newUrls.add(newUrl);

        user.setUrls(newUrls);

        userDao.save(user);

        return  new SuccessResult();
    }
}
