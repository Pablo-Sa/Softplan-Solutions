package br.com.softplan.poc.dto.erros;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorPostExceptionDto {
	
	private String campo;
	private String erro;

}