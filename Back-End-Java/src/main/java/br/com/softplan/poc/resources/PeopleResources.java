package br.com.softplan.poc.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.poc.entity.People;
import br.com.softplan.poc.service.PeopleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Api(value = "Back-End Java - POC SoftPlan")
public class PeopleResources {
	
	@Autowired
	PeopleService peopleService;
	
	@ApiOperation(value = "Retorna Todos as Pessoas")
	@GetMapping("/people")
	public ResponseEntity<?> getAllPeople() {
		return new ResponseEntity<>(peopleService.findAll(), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Retorna um registro de Pessoa Por ID")
	@GetMapping("/people/{id}")
	public ResponseEntity<People> getPeopleId(@PathVariable("id") Long id) {
		Optional<People> people = (Optional<People>) peopleService.findById(id);
		
		if(people.isPresent()) {
			return ResponseEntity.ok(people.get());
		  }
		
		return ResponseEntity.notFound().build();

	}
	
	
	
	
	
	
	
	

}
