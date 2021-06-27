package tapu.urlshortener.business.concretes;


import org.springframework.stereotype.Service;
import tapu.urlshortener.business.abstracts.UrlMapService;
import tapu.urlshortener.business.abstracts.UrlService;
import tapu.urlshortener.business.abstracts.UserService;
import tapu.urlshortener.core.utilities.results.*;
import tapu.urlshortener.core.utilities.utility.UrlShortenerUtilService;
import tapu.urlshortener.dataAccess.abstracts.UrlDao;
import tapu.urlshortener.dataAccess.abstracts.UserDao;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.UserWithUrlMapResponse;

import java.util.HashSet;
import java.util.List;

@Service
public class UrlMapManager implements UrlMapService {

    private UserService userService;
    private UrlService urlService;
    private UrlShortenerUtilService urlShortenerUtilService;

    public UrlMapManager(UrlService urlService, UserService userService, UrlShortenerUtilService urlShortenerUtilService) {
        this.urlService = urlService;
        this.userService = userService;
        this.urlShortenerUtilService = urlShortenerUtilService;
    }

    @Override
    public DataResult<List<UserWithUrlMapResponse>> getByCreatedUser(int userId) {
        return new SuccessDataResult<>(urlService.getUrlsByCreatedUserId(userId));
    }

    public Result addUrlIntoUser(int userId, String toLink){
        var userDataResult = checkUserById(userId);
        if(userDataResult.isSuccess()){
            return addOrUpdateUserUrl(userDataResult.getData(), toLink);
        }
        return new ErrorResult("User could not found.");
    }



    @Override
    public Result deleteUrlFromUser(int userId, int urlId) {
        var userDataResult = checkUserById(userId);

        if(userDataResult.isSuccess()){
            var checkUrlResult = checkUrlByToLink(urlId);
            if(checkUrlResult.isSuccess()){
                return deleteUrlFromUser(userDataResult.getData(), checkUrlResult.getData());
            }
            return checkUrlResult;
        }
        return new ErrorResult("User could not found.");
    }
    private Result deleteUrlFromUser(User user, Url url){
        var errorResult = checkUserHasUrl(user, url);
        if(errorResult.isSuccess()){
            var newUrls = new HashSet<Url>();
            for(Url u: user.getUrls()){
                if(u.getId() == url.getId())continue;
                newUrls.add(u);
            }
            //user.getUrls().removeIf(t-> t.getId() == url.getId());
            userService.save(user);
            return new SuccessResult("Url deleted successfully");
        }
        return errorResult;

    }

    private DataResult<Url> checkUrlByToLink(int urlId){
        var urlIsExist = urlService.getById(urlId);
        if(urlIsExist.isSuccess()){
            return new SuccessDataResult<>(urlIsExist.getData());
        }
        return new ErrorDataResult<>("Url could not find.");
    }


    private DataResult<User> checkUserById(int userId){
        var user = userService.getUserById(userId);
        if(user!= null){
            return new SuccessDataResult<>(user);
        }
        return new ErrorDataResult<>("User could not found.");
    }


    private Result addOrUpdateUserUrl(User user, String toLink){
        var url = urlService.addUrlOrGet(toLink);
        //var user = userService.getUserById(userId);

        var result = getErrorResult(user, url);

        if(result.isSuccess()){
            return addUrlIntoUser(user, url);
        }else{
            return result;
        }

    }

    private Result addUrlIntoUser(User user, Url url) {
        var isAdded = user.getUrls().add(url);

        if(isAdded) {
            userService.save(user);
            return new SuccessResult("Url is added into the user urls");
        }else{
            return new ErrorResult("Url is not added into the user");
        }
    }

    private Result getErrorResult(User user, Url url) {
        if(user.getUrls().stream().filter(u -> u.getId() == url.getId() )
                .findAny().orElse(null) != null){
            return new ErrorResult("The user is already has the url");
        }
        return new SuccessResult();
    }
    public Result checkUserHasUrl(User user, Url url) {
        if(user.getUrls().stream().filter(u -> u.getId() == url.getId() )
                .findAny().orElse(null) == null){
            return new ErrorResult("The url already been removed for current user.");
        }
        return new SuccessResult();
    }
}
