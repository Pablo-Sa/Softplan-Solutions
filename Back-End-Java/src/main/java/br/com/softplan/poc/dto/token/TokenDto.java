package br.com.softplan.poc.dto.token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TokenDto {

	private String token;
	private String type;
	
	public TokenDto(String token, String type) {
		this.token = token;
		this.type = type;
	}

}
