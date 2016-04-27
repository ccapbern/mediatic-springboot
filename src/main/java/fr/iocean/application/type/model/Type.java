package fr.iocean.application.type.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.iocean.application.media.model.Media;

@Entity
@Table(name = "types")
public class Type implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @SequenceGenerator(name = "type_id_sequence", sequenceName = "type_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "type_id_sequence")
    private Long id;
    @NotBlank
    private String name;

    public Type() {

    }

    public Type(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
