package tapu.urlshortener.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import tapu.urlshortener.entities.concretes.Url;

public interface UrlMapDao  extends JpaRepository<Url, Integer> {
}
