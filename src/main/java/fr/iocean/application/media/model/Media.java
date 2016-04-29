package fr.iocean.application.media.model;

import fr.iocean.application.persistence.IoEntity;
import fr.iocean.application.type.model.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "media")
public class Media implements IoEntity {

    @Id
    @SequenceGenerator(name = "media_id_sequence", sequenceName = "media_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "media_id_sequence")
    private Long id;
    
    @NotBlank
    private String title;
    
    @NotBlank
    private String author;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeEnum type;
    
}
