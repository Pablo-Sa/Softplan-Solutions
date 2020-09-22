package br.com.softplan.poc.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

	@NotEmpty
	private String name;
	private String sexo;
	@Email(message = "Formato do E-mail Inválido")
	private String email;
	@NotNull
	private Date dateOfBirth;
	private String naturalness;
	private String nationality;
	@NotEmpty
	@CPF
	private String cpf;

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("name", name).append("sexo", sexo).append("email", email)
				.append("dateOfBirth", dateOfBirth).append("naturalness", naturalness)
				.append("nationality", nationality).append("cpf", cpf).toString();
	}
}
