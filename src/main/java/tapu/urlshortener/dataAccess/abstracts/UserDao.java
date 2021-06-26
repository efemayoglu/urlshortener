package tapu.urlshortener.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.UserWithUrlMapResponse;

import java.util.List;
public interface UserDao extends JpaRepository<User, Integer> {

    User getByUsernameAndPassword(String username, String password);

    User getUserById(int userId);

    /*@Query("select new tapu.urlshortener.entities.concretes.User() from User us inner join us.urls ur where us.id = :userId")
    List<UserWithUrlMapResponse> getUserWithUrls(@Param("userId") int userId);*/

}
