package br.com.softplan.poc.dto.accesscredentials;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import br.com.softplan.poc.entity.AccessCredentials;

@Getter
@Setter
@NoArgsConstructor
public class AccessCredentialsDto {
	
	private String login;
	private String password;
	
	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(this.login, this.password);
	}

	public AccessCredentials convertUser() {
		AccessCredentials user = new AccessCredentials();
		user.setLogin(this.login);
		user.setPasswd(this.password);
		return user;
	}

}
