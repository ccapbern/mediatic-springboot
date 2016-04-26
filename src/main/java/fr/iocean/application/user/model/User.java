
package fr.iocean.application.user.model;

import fr.iocean.application.persistence.IoEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements IoEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "users_id_sequence", sequenceName = "users_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "users_id_sequence")
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private boolean authorize;
}
