package fr.iocean.application.borrow.model;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.member.model.Member;
import fr.iocean.application.persistence.IoEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "borrow")
@Getter
@Setter
public class Borrow implements IoEntity {

	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "borrow_id_sequence", sequenceName = "borrow_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "borrow_id_sequence")
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Media media;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date borrowing_date;

    @Temporal(TemporalType.DATE)
    private Date return_date;
}
