package br.com.softplan.poc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.poc.service.LogCreateAndUpdateService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
public class LogCreateAndUpdateResource {
	
	
	@Autowired
	LogCreateAndUpdateService logCreateAndUpdateService;
	
	@ApiOperation(value = "Retorna Log de Criação e Alteração dos Registros")
	@GetMapping("/log")
	public ResponseEntity<?> getAllLogs() {
		return new ResponseEntity<>(logCreateAndUpdateService.findAll(), HttpStatus.OK);
	}

}
