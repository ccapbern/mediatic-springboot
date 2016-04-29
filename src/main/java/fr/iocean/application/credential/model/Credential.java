package fr.iocean.application.credential.model;


import fr.iocean.application.persistence.IoEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "credential")
@Getter
@Setter
public class Credential implements IoEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	private String value;
}
