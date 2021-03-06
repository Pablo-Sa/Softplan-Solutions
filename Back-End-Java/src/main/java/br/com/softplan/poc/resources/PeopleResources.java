package br.com.softplan.poc.resources;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.softplan.poc.entity.LogCreateAndUpdate;
import br.com.softplan.poc.entity.People;
import br.com.softplan.poc.service.LogCreateAndUpdateService;
import br.com.softplan.poc.service.PeopleService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PeopleResources {

	@Autowired
	PeopleService peopleService;

	@Autowired
	LogCreateAndUpdateService logCreateAndUpdateService;

	@ApiOperation(value = "Retorna Todos as Pessoas")
	@GetMapping("/people")
	public ResponseEntity<?> getAllPeople() {
		return new ResponseEntity<>(peopleService.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Retorna um registro de Pessoa Por ID")
	@GetMapping("/people/{id}")
	public ResponseEntity<People> getPeopleId(@PathVariable("id") Long id) {
		Optional<People> people = (Optional<People>) peopleService.findById(id);

		if (people.isPresent()) {
			return ResponseEntity.ok(people.get());
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Salva uma Pessoa")
	@PostMapping(path = "/people")
	public ResponseEntity<?> savePeople(@RequestBody @Valid People people) {

		Optional<People> person = (Optional<People>) peopleService.findByCpf(people.getCpf());
		if (person.isPresent()) {
			return new ResponseEntity("Este CPF já Está Cadastrado na Base de Dados", HttpStatus.BAD_REQUEST);
		}

		People newPerson = peopleService.save(people);

		LogCreateAndUpdate logCreatePeople = new LogCreateAndUpdate(newPerson.getId(), newPerson.getName(),
				new Date(System.currentTimeMillis()), null, null);

		logCreateAndUpdateService.save(logCreatePeople);

		return new ResponseEntity<People>(newPerson, HttpStatus.OK);
	}

	@ApiOperation(value = "Atualiza um Registro")
	@PutMapping("/people")
	public ResponseEntity<People> updatePeople(@RequestBody @Valid People people) {
		Optional<People> dto = (Optional<People>) peopleService.findById(people.getId());

		if (dto.isPresent()) {

			People updatedPerson = peopleService.save(people);

			logCreateAndUpdateService.saveLogChange(updatedPerson.getId(), new Date(System.currentTimeMillis()));

			return new ResponseEntity<People>(updatedPerson, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Delete um Registro de Pessoa")
	@DeleteMapping("/people")
	public ResponseEntity<People> deletePeople(@RequestBody People people) {

		Optional<People> dto = (Optional<People>) peopleService.findById(people.getId());

		if (dto.isPresent()) {
			peopleService.delete(people);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Deletar uma registro de Pessoa por ID")
	@DeleteMapping("/people/{id}")
	public ResponseEntity<?> deletePeopleId(@PathVariable("id") Long id) {
		Optional<People> people = (Optional<People>) peopleService.findById(id);

		if (people.isPresent()) {

			logCreateAndUpdateService.saveLogDelete(id, new Date(System.currentTimeMillis()));
			return new ResponseEntity<>(peopleService.deleteById(id), HttpStatus.OK);
		}

		return ResponseEntity.notFound().build();
	}

}
