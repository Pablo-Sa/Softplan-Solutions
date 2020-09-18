package br.com.softplan.poc.config.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.softplan.poc.entity.AccessCredentials;
import br.com.softplan.poc.service.AccessCredentialsService;
import br.com.softplan.poc.service.TokenService;

public class AutenticationTokenFilter extends OncePerRequestFilter{

	private AccessCredentialsService accessCredentialsService;
	private TokenService tokenService;
		
	public AutenticationTokenFilter(TokenService tokenService, AccessCredentialsService accessCredentialsService) {
		this.tokenService = tokenService;
		this.accessCredentialsService = accessCredentialsService;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = recoverToken(request);
		System.out.println("Token Recuperado");
		System.out.println(token);
		
		boolean tokenValid = tokenService.isTokenValid(token);
		if(tokenValid) {
			authenticateClient(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void authenticateClient(String token) {
		Long idUser = this.tokenService.getIdUser(token);
		AccessCredentials user = this.accessCredentialsService.findById(idUser).get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recoverToken(HttpServletRequest request) {
		String tokenRecover = request.getHeader("Authorization");
		
		if(tokenRecover == null || tokenRecover.isEmpty() || !tokenRecover.startsWith("Bearer ")) {
			return null;
		}
		
		return tokenRecover.substring(7, tokenRecover.length());
	}

}
