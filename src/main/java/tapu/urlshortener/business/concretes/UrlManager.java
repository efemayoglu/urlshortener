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
    public DataResult<Url> addUrl(String toLink) {

        var isExist = urlDao.getUrlByToLink(toLink);

        if(isExist != null){
            return new ErrorDataResult("Already exist.");
        } else{
            var fromLink = urlShortenerUtilService.create(toLink);
            var url = new Url();
            url.setFromLink(fromLink);
            url.setToLink(toLink);
            var result = urlDao.save(url);
            System.out.println(result.getId());
            return new SuccessDataResult<>(result);
        }

    }
}
