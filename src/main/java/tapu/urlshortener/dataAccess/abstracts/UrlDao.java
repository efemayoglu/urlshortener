package tapu.urlshortener.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.UserWithUrlMapResponse;

import java.util.List;

public interface UrlDao extends JpaRepository<Url, Integer> {

    //List<Url> getUrlsByCreatedUser(int userId);

    Url getUrlByFromLink(String fromLink);

    Url getUrlByToLink(String toLink);

    Url getUrlById(int id);

    @Query("select new tapu.urlshortener.entities.dtos.UserWithUrlMapResponse(us.id, ur.id, us.username, ur.fromLink, ur.toLink) from User us inner join us.urls ur where us.id = :userId")
    List<UserWithUrlMapResponse> getUrlsByCreatedUserId(@Param("userId") int userId);

}
