package br.com.softplan.poc.resources;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SourceResource {
	
	@ApiOperation(value = "Retorna URL do Projeto no GitHub")
	@GetMapping("/source")
	public String getSource() {
		return "https://github.com/Pablo-Sa/Softplan-Solutions"; 
	}

}
