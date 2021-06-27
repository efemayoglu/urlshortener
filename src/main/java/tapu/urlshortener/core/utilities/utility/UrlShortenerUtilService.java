package tapu.urlshortener.core.utilities.utility;
public interface UrlShortenerUtilService {
    String create(String url);
    String getUrl(String id);
    String getValidUrl(String url);
}
