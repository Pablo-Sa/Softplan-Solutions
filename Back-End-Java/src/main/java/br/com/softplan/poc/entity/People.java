package br.com.softplan.poc.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

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
	private String email;
	private Date dateOfBirth;
	private String naturalness;
	private String nationality;
	@NotEmpty
	@CPF
	private String cpf;

}
