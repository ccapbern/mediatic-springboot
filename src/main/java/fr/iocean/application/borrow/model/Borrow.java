package fr.iocean.application.borrow.model;

import fr.iocean.application.medias.model.Medias;
import fr.iocean.application.members.model.Members;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "borrow")
public class Borrow implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @SequenceGenerator(name = "borrow_id_sequence", sequenceName = "borrow_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "borrow_id_sequence")
    private Long id;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Members member;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "media_id")
    private Medias media;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date borrowing_date;
    @Temporal(TemporalType.DATE)
    private Date return_date;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the member_id
     */
    public Members getMember() {
        return member;
    }

    /**
     * @param member the member_id to set
     */
    public void setMember(Members member) {
        this.member = member;
    }

    /**
     * @return the media_id
     */
    public Medias getMedia() {
        return media;
    }

    /**
     * @param media the member_id to set
     */
    public void setMedia(Medias media) {
        this.media = media;
    }

    /**
     * @return the borrowing_date
     */
    public Date getBorrowing_date() {
        return borrowing_date;
    }

    /**
     * @param borrowing_date the borrowing_date to set
     */
    public void setBorrowing_date(Date borrowing_date) {
        this.borrowing_date = borrowing_date;
    }

    /**
     * @return the return_date
     */
    public Date getReturn_date() {
        return return_date;
    }

    /**
     * @param return_date the return_date to set
     */
    public void setReturn_date(Date return_date) {
        this.return_date = return_date;
    }
}
