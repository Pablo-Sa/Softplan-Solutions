package br.com.softplan.poc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.poc.entity.People;
import br.com.softplan.poc.repository.PeopleRepository;

@Service
public class PeopleService {

	@Autowired
	private PeopleRepository peopleRepository;

	public People save(People people) {
		return peopleRepository.save(people);
	}

	public Optional<People> findById(Long id) {
		return peopleRepository.findById(id);
	}

	public People delete(People people) {
		peopleRepository.delete(people);
		return people;
	}

	public double deleteById(Long id) {
		peopleRepository.deleteById(id);
		return id;
	}

	public Iterable<People> findAll() {
		return peopleRepository.findAll();
	}

}
