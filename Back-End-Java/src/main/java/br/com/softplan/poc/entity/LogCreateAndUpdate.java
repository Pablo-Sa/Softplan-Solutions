package br.com.softplan.poc.entity;

import java.util.Date;

import javax.persistence.Entity;

import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LogCreateAndUpdate extends AbstractEntity {
	
	private Long idPerson;
	private String namePerson;
	private Date dateOfCreate;
	private Date dateOfUpdate;
	private Date dateOfExclusion;
	
	@Override
	public String toString() {
		return new ToStringBuilder(this)
			.append("idPeople", idPerson)
			.append("idPeople", namePerson)
			.append("dateOfCreate", dateOfCreate)
			.append("dateOfUpdate", dateOfUpdate)
			.append("dateOfExclusion", dateOfExclusion)
			.toString();
		}

}
