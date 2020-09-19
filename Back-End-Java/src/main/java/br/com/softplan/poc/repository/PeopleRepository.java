package br.com.softplan.poc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.poc.entity.People;

@Repository
public interface PeopleRepository extends CrudRepository<People, Long>{

}
