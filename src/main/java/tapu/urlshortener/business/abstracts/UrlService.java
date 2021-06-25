package tapu.urlshortener.business.abstracts;

import tapu.urlshortener.core.utilities.results.DataResult;
import tapu.urlshortener.entities.concretes.Url;

public interface UrlService {

    DataResult<Url> getUrlByFromLink(String fromLink);

}
