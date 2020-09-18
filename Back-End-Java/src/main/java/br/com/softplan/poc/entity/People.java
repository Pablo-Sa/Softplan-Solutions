package br.com.softplan.poc.entity;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class People extends AbstractEntity {

	private String name;
	private String sexo;
	private String email;
	private Date dateOfBirth;
	private String naturalness;
	private String nationality;
	private String cpf;

}
