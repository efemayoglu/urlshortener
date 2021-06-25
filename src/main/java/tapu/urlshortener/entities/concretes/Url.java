package tapu.urlshortener.entities.concretes;


import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Data
@Entity
@Table(name = "url_maps")
public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    private int id;

    @ManyToMany
    @JoinTable(
            name = "user_url_map",
            joinColumns = @JoinColumn(name = "url_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> createdUser;


    @Column(name = "url_fromLink")
    @NotBlank
    @NotNull
    private String fromLink;

    @Column(name = "url_toLink")
    @NotBlank
    @NotNull
    private String toLink;



}
