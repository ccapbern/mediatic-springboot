package fr.iocean.application.member.model;

import fr.iocean.application.persistence.IoEntity;
import fr.iocean.application.utils.CalendarUtil;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "member")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"firstname","lastname"})
public class Member implements IoEntity {

	private static final long serialVersionUID = 1L;
	
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
    private String address1;
    private String address2;
    private String zipcode;
    private String city;

    @Temporal(TemporalType.DATE)
    private Date subscription_date;
    private Integer subscription_amount;

    @Transient
    private Integer age;

    public Integer getAge() {
        age = 0;
        if (this.dob == null) {
            return age;
        }
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
