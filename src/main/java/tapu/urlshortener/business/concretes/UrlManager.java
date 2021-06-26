package tapu.urlshortener.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.business.abstracts.UrlService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.core.utilities.results.SuccessDataResult;
import tapu.urlshortener.dataAccess.abstracts.UrlDao;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.dtos.UserWithUrlMapResponse;

import java.util.List;

@Service
public class UrlManager implements UrlMapService, UrlService {
    private UrlDao urlDao;

    @Autowired
    public UrlManager(UrlDao urlDao){
        this.urlDao = urlDao;
    }


    public DataResult<List<UserWithUrlMapResponse>> getByCreatedUser(int userId){

        return new SuccessDataResult<>(urlDao.getUrlsByCreatedUserId(userId),"Urls Successfully listed by userId");
    }

    @Override
    public DataResult<Url>  getUrlByFromLink(String fromLink) {
        return new SuccessDataResult<>(urlDao.getUrlByFromLink(fromLink));
    }
}
