package br.com.softplan.poc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.poc.dto.accesscredentials.AccessCredentialsDto;
import br.com.softplan.poc.dto.token.TokenDto;
import br.com.softplan.poc.service.TokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AutenticationResource {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;	  
	
	@ApiOperation(value = "EndPoint Destinado a Login na API")
	@PostMapping
	public ResponseEntity<TokenDto> authenticate(@RequestBody AccessCredentialsDto form){
		UsernamePasswordAuthenticationToken dataLogin = form.converter();
		
		try { 
			Authentication authentication = authManager.authenticate(dataLogin);
			String token = tokenService.generateToken(authentication);
			HttpHeaders responseHeaders = new HttpHeaders();
		    responseHeaders.set("Authorization","Bearer "+token);
			System.out.println("Token Abaixo");
			System.out.println(token);
			return ResponseEntity.ok()
				   .headers(responseHeaders)
				   .body(new TokenDto(token, "Bearer"));
		 	 
		} catch (AuthenticationException e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().build();
		}

	}

}

