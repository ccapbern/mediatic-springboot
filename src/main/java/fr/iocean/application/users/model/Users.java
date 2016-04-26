
package fr.iocean.application.users.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="users")
public class Users implements Serializable {

    @Id
    @SequenceGenerator(name="users_id_sequence", sequenceName = "users_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "users_id_sequence")
    private Long id;
    @NotBlank
    private String login;
    @NotBlank
    private String password;
    @NotBlank
    private String email;
    private boolean authorize;
    
    public Users(){
    }

    public Users(String login, String password, String email, boolean authorize) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.authorize = authorize;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAuthorize() {
        return authorize;
    }

    public void setAuthorize(boolean authorize) {
        this.authorize = authorize;
    }    
}
