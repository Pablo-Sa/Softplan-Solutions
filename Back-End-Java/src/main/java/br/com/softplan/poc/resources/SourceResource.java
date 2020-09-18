package br.com.softplan.poc.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Back-End Java - POC SoftPlan")
public class SourceResource {
	
	@ApiOperation(value = "Retorna URL do Projeto no GitHub")
	@GetMapping("/source ")
	public ResponseEntity<String> getAllPeople() {
		return new ResponseEntity<String>("", HttpStatus.OK);
	}

}
