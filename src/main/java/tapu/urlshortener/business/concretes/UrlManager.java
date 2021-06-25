package tapu.urlshortener.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.core.utilities.results.SuccessDataResult;
import tapu.urlshortener.dataAccess.abstracts.UrlDao;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.dtos.UserWithUrlMap;

import java.util.List;

@Service
public class UrlManager implements UrlMapService {
    private UrlDao urlDao;

    @Autowired
    public UrlManager(UrlDao urlDao){
        this.urlDao = urlDao;
    }


    public DataResult<List<UserWithUrlMap>> getByCreatedUser(int userId){

        return new SuccessDataResult<>(urlDao.getUrlsByCreatedUserId(userId),"Urls Successfully listed by userId");
    }

}
