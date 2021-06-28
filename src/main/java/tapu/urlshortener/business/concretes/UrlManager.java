package tapu.urlshortener.business.concretes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.business.abstracts.UrlService;
import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.core.utilities.results.ErrorDataResult;
import tapu.urlshortener.core.utilities.results.SuccessDataResult;
import tapu.urlshortener.core.utilities.utility.UrlShortenerUtilService;
import tapu.urlshortener.dataAccess.abstracts.UrlDao;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.dtos.UserWithUrlMapResponse;

import java.util.List;

@Service
public class UrlManager implements UrlService {

    private UrlDao urlDao;
    private UrlShortenerUtilService urlShortenerUtilService;

    @Autowired
    public UrlManager(UrlDao urlDao, UrlShortenerUtilService urlShortenerUtilService){
        this.urlDao = urlDao;
        this.urlShortenerUtilService = urlShortenerUtilService;
    }


    public DataResult<List<UserWithUrlMapResponse>> getByCreatedUser(int userId){

        return new SuccessDataResult<>(urlDao.getUrlsByCreatedUserId(userId),"Urls Successfully listed by userId");
    }

    @Override
    public DataResult<Url>  getUrlByFromLink(String fromLink) {
        return new SuccessDataResult<>(urlDao.getUrlByFromLink(fromLink));
    }

    @Override
    public DataResult<Url> getUrlByToLink(String toLink) {
        return new SuccessDataResult<>(urlDao.getUrlByToLink(toLink));
    }

    @Override
    public DataResult<Url> getById(int id) {
        Url result = urlDao.getById(id);
        if(result != null)
            return new SuccessDataResult<>(result);
        return new ErrorDataResult<>("url could not found");
    }

    @Override
    public Url addUrlOrGet(String toLink) {
        return addUrlOrGetExists(toLink);
    }

    @Override
    public List<UserWithUrlMapResponse> getUrlsByCreatedUserId(int userId) {
        return urlDao.getUrlsByCreatedUserId(userId);
    }

    private Url addUrlOrGetExists(String toLink) {
        String validLink = urlShortenerUtilService.getValidUrl(toLink);
        Url isExist = urlDao.getUrlByToLink(validLink);

        if (isExist != null) {
            return isExist;
        } else {
            String fromLink = urlShortenerUtilService.create(validLink);
            Url url = new Url();
            url.setFromLink(fromLink);
            url.setToLink(validLink);
            Url result = urlDao.save(url);
            return result;
        }
    }
}
