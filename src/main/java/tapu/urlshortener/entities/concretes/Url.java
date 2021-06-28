package tapu.urlshortener.entities.concretes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Data
@Entity
@Table(name = "url_maps")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer","handler","createdUser"})

public class Url {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "url_id")
    private int id;

    @ManyToMany(mappedBy = "urls", fetch = FetchType.LAZY)
    private Set<User> createdUser = new HashSet<User>();

    @Column(name = "url_from_link")
    @NotBlank
    @NotNull
    private String fromLink;

    @Column(name = "url_to_link")
    @NotBlank
    @NotNull
    private String toLink;



}
