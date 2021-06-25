package tapu.urlshortener.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.UserWithUrlMap;

import java.util.List;

public interface UrlDao extends JpaRepository<Url, Integer> {

    List<Url> getUrlsByCreatedUser(int userId);


    @Query("select new tapu.urlshortener.entities.dtos.UserWithUrlMap(us.id, ur.id, us.username, ur.fromLink, ur.toLink) from User us inner join us.urls ur where us.id = :userId")
    List<UserWithUrlMap> getUrlsByCreatedUserId(@Param("userId") int userId);

}
