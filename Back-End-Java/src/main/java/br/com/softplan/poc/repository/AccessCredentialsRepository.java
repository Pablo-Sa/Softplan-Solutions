package br.com.softplan.poc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.softplan.poc.entity.AccessCredentials;

@Repository
public interface AccessCredentialsRepository extends CrudRepository<AccessCredentials, Long>{
	
	public Optional<AccessCredentials> findByLogin(String login);

}
