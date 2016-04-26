package fr.iocean.application.borrow.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.member.model.Member;

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

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the member_id
     */
    public Member getMember() {
        return member;
    }

    /**
     * @param member the member_id to set
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     * @return the media_id
     */
    public Media getMedia() {
        return media;
    }

    /**
     * @param media the member_id to set
     */
    public void setMedia(Media media) {
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
