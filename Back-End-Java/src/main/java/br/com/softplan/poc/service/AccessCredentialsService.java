package br.com.softplan.poc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.softplan.poc.entity.AccessCredentials;
import br.com.softplan.poc.repository.AccessCredentialsRepository;

@Service
public class AccessCredentialsService {
	
	@Autowired
	public AccessCredentialsRepository accessCredentialsRepository;

	public AccessCredentials save(AccessCredentials accessCredentials) {
        return accessCredentialsRepository.save(accessCredentials);
    }
	
	public Iterable<AccessCredentials> findAll() {
        return accessCredentialsRepository.findAll();
    }
	
	public Optional<AccessCredentials> findByLogin(String login) {
		return accessCredentialsRepository.findByLogin(login);
	}
	
	public Optional<AccessCredentials> findById(Long id) {
		return accessCredentialsRepository.findById(id);
	}

}
