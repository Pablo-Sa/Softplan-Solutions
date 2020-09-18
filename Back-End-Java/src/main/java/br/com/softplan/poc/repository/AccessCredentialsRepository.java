package br.com.softplan.poc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.softplan.poc.entity.AccessCredentials;

public interface AccessCredentialsRepository extends CrudRepository<AccessCredentials, Long>{
	
	public Optional<AccessCredentials> findByLogin(String login);

}
