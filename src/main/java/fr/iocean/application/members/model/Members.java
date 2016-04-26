package fr.iocean.application.members.model;

import fr.iocean.application.borrow.model.Borrow;
import fr.iocean.application.utils.CalendarUtil;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "members")
public class Members implements Serializable {

    @Id
    @SequenceGenerator(name = "member_id_sequence", sequenceName = "member_id_sequence", allocationSize = 1)
    @GeneratedValue(generator = "member_id_sequence")
    private Long id;
    @NotBlank
    private String lastname;
    @NotBlank
    private String firstname;
    @NotNull
    @Temporal(TemporalType.DATE)
    private Date dob;
    @NotBlank
    private String email;
    private String address;
    private String city;
    @Temporal(TemporalType.DATE)
    private Date subscription_date;
    private Integer subscription_amount;
    @OneToMany(mappedBy = "member")
    private List<Borrow> borrow;
    @Transient
    private Integer age;

    public Members() {
    }

    public Members(String lastname, String firstname, Date dob, String email) {
        this.lastname = lastname.toUpperCase().trim();
        this.firstname = firstname.trim();
        this.dob = dob;
        this.email = email.trim();
        this.age = getAge();
    }

    @Override
    public String toString() {
        return this.firstname + " " + this.lastname;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the lastname
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * @param lastname the lastname to set
     */
    public void setLastname(String lastname) {
        this.lastname = lastname.toUpperCase().trim();
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname.trim();
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email.trim();
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address.trim();
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city.trim();
    }

    /**
     * @return the subscription_date
     */
    public Date getSubscription_date() {
        return subscription_date;
    }

    /**
     * @param subscription_date the subscription_date to set
     */
    public void setSubscription_date(Date subscription_date) {
        this.subscription_date = subscription_date;
    }

    /**
     * @return the subscription_amount
     */
    public Integer getSubscription_amount() {
        return subscription_amount;
    }

    /**
     * @param subscription_amount the subscription_amount to set
     */
    public void setSubscription_amount(Integer subscription_amount) {
        this.subscription_amount = subscription_amount;
    }

    /**
     * @return the borrow
     */
    public List<Borrow> getBorrow() {
        return borrow;
    }

    /**
     * @return the age
     */
    public Integer getAge() {
        age = 0;
        Calendar birth = CalendarUtil.getCalendar(this.dob);
        Calendar now = CalendarUtil.getCalendar(new Date());

        age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        if (birth.get(Calendar.MONTH) > now.get(Calendar.MONTH)
                || (birth.get(Calendar.MONTH) == now.get(Calendar.MONTH)
                && birth.get(Calendar.DATE) > now.get(Calendar.DATE))) {
            age--;
        }

        return age;
    }
}
