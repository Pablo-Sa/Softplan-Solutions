package br.com.softplan.poc.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.softplan.poc.entity.People;

public interface PeopleRepository extends CrudRepository<People, Long>{

}
