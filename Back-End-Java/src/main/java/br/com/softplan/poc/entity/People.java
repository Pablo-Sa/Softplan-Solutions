package br.com.softplan.poc.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.br.CPF;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class People extends AbstractEntity {

	@NotEmpty
	private String name;
	private String sexo;
	@Email
	private String email;
	@NotNull
	private Date dateOfBirth;
	private String naturalness;
	private String nationality;
	@NotEmpty
	@CPF
	private String cpf;

}
