package tapu.urlshortener.entities.concretes;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@Entity
@Table(name = "url_maps")
public class UrlMap {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    private int id;

    @Column(name = "url_fromLink")
    @NotBlank
    @NotNull
    private String fromLink;

    @Column(name = "url_toLink")
    @NotBlank
    @NotNull
    private String toLink;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "url_maps")
    Set<UserUrlMap> registrations;

}
