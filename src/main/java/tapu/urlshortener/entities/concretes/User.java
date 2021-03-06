package tapu.urlshortener.entities.concretes;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private int id;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_url_map",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "url_id")
    )
    private List<Url> urls = new ArrayList<>();


    @Column(name = "user_name")
  //  @NotBlank
   ///@NotNull
    private String username;

    @Column(name = "user_password")
   // @NotBlank
   // @NotNull
    private String password;



}
