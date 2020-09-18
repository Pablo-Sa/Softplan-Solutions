package br.com.softplan.poc.config.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.softplan.poc.entity.AccessCredentials;
import br.com.softplan.poc.service.AccessCredentialsService;

@Service
public class AutenticationService implements UserDetailsService {
	
	@Autowired
	private AccessCredentialsService accessCredentialsService;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Optional<AccessCredentials> user = accessCredentialsService.findByLogin(login);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UsernameNotFoundException("Dados Inválidos !");
	}
}

