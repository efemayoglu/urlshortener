package tapu.urlshortener.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tapu.urlshortener.entities.concretes.Url;
import tapu.urlshortener.entities.concretes.User;
import tapu.urlshortener.entities.dtos.UserWithUrlMap;

import java.util.List;
public interface UserDao extends JpaRepository<User, Integer> {

    User getByUsernameAndPassword(String username, String password);


}
