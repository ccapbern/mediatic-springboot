
package fr.iocean.application.user.model;

import fr.iocean.application.credential.model.Credential;
import fr.iocean.application.persistence.IoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements IoEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Credential> credentials;
}
