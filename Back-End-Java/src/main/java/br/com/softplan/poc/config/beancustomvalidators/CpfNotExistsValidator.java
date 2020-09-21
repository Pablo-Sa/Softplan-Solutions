package br.com.softplan.poc.config.beancustomvalidators;

import java.util.Optional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import com.google.common.base.Strings;

import br.com.softplan.poc.entity.People;
import br.com.softplan.poc.service.PeopleService;

public class CpfNotExistsValidator implements ConstraintValidator<CpfAlreadyExists, String> {

	private String value;

	@Autowired
	private PeopleService peopleService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

		if (!Strings.isNullOrEmpty(value)) {

			try {
				Optional<People> person = (Optional<People>) peopleService.findByCpf(value);
				if (person.isPresent()) {
					return false;
				}

			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

		}

		return true;
	}
}
