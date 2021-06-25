package tapu.urlshortener.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import tapu.urlshortener.entities.concretes.User;

public interface ProductDao extends JpaRepository<User, Integer> {
}
