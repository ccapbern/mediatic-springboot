package fr.iocean.application.media.model;

import fr.iocean.application.type.model.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "media")
public class Media {

    @Id
    @SequenceGenerator(name = "media_id_sequence", sequenceName = "media_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "media_id_sequence")
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String author;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "type_id")
    private Type type;

}
