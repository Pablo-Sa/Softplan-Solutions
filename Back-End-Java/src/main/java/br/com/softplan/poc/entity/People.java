package br.com.softplan.poc.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.validator.constraints.br.CPF;

import br.com.softplan.poc.config.beancustomvalidators.CpfAlreadyExists;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class People extends AbstractEntity {

	public interface PeopleCreation {}

	@NotEmpty
	private String name;
	private String sexo;
	@Email
	private String email;
	@NotNull
	private Date dateOfBirth;
	private String naturalness;
	private String nationality;
	@CpfAlreadyExists(groups = { PeopleCreation.class }, message = "Não Foi Possível Realizar o Cadastro, Este CPF já existe na Base de Dados")
	@NotEmpty
	@CPF
	private String cpf;

	@Override
	public String toString() {
		return new ToStringBuilder(this)
				.append("nome", name)
				.append("sexo", sexo)
				.append("email", email)
				.append("dateOfBirth", dateOfBirth)
				.append("naturalness", naturalness)
				.append("nationality", nationality)
				.append("cpf", cpf).toString();
	}

}
