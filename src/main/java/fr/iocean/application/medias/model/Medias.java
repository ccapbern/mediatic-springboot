package fr.iocean.application.medias.model;

import fr.iocean.application.type.model.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "medias")
public class Medias {

    @Id
    @SequenceGenerator(name = "medias_id_sequence", sequenceName = "medias_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "medias_id_sequence")
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

    public Medias() {

    }

    public Medias(Long id, String title, String author, Type type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

}
