package br.com.softplan.poc.endpoints.people;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.softplan.poc.dto.token.TokenDto;
import br.com.softplan.poc.entity.AccessCredentials;
import br.com.softplan.poc.entity.People;
import br.com.softplan.poc.service.PeopleService;


import java.util.ArrayList;
import java.sql.Date;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class PeopleEndPointTest {

	@Test
	void contextLoads() {

	}

	@Autowired
	private TestRestTemplate restTemplet;

	@LocalServerPort
	private int port;

	@MockBean
	private PeopleService peopleService;

	private HttpEntity<Void> protectedHeader;
	private HttpEntity<Void> wrongHeader;

	@BeforeEach
	public void configProtectedHeaders() {
		AccessCredentials form = new AccessCredentials();
		form.setLogin("Pablo");
		form.setPasswd("Mudar@123");
		HttpHeaders headers = restTemplet.postForEntity("/auth", form, String.class).getHeaders();
		this.protectedHeader = new HttpEntity<>(headers);
	}

	@BeforeEach
	public void configWrongHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "TokenInválido");
		this.wrongHeader = new HttpEntity<>(headers);
	}

	@Test
	public void PostLoginResponseStatusCode200() {
		AccessCredentials form = new AccessCredentials();
		form.setLogin("Pablo");
		form.setPasswd("Mudar@123");
		ResponseEntity<TokenDto> response = this.restTemplet.postForEntity("/auth", form, TokenDto.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void PostLoginResponseStatusCode400() {
		AccessCredentials form = new AccessCredentials();
		form.setLogin("Pablo");
		form.setPasswd("Mudar@123f");
		ResponseEntity<TokenDto> response = this.restTemplet.postForEntity("/auth", form, TokenDto.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}

	@Test
	public void GetAllResponseStatusCode200() {

		People person = new People();
		person.setName("Pablo");
		person.setCpf("11220312622");
		person.setSexo("Masculino");
		person.setDateOfBirth(new Date(System.currentTimeMillis()));

		People personTwo = new People();
		personTwo.setName("Pablo");
		personTwo.setCpf("11220312622");
		personTwo.setSexo("Masculino");
		personTwo.setDateOfBirth(new Date(System.currentTimeMillis()));

		peopleService.save(person);
		ArrayList<People> people = new ArrayList<People>();

		BDDMockito.when(this.peopleService.findAll()).thenReturn(people);
		ResponseEntity<String> response = restTemplet.exchange("/people", HttpMethod.GET, this.protectedHeader,
				String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void GetAllResponseStatusCode403() {
		ResponseEntity<String> response = restTemplet.exchange("/people", HttpMethod.GET, this.wrongHeader,
				String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(403);
	}

	@Test
	public void GetAllResponseStatusCode404() {
		ArrayList<People> people = new ArrayList<People>();
		BDDMockito.when(this.peopleService.findAll()).thenReturn(people);
		ResponseEntity<String> response = restTemplet.exchange("/cursosf", HttpMethod.GET, this.protectedHeader,
				String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void GetByIdResponseStatusCode200() {

		Optional<People> person = Optional.of(new People());
		person.get().setId(new Long(100));
		person.get().setName("Pablo");
		person.get().setCpf("11220312622");
		person.get().setSexo("Masculino");
		person.get().setDateOfBirth(new Date(System.currentTimeMillis()));
		peopleService.save(person.get());

		BDDMockito.when(this.peopleService.findById(person.get().getId())).thenReturn(person);
		ResponseEntity<String> response = restTemplet.exchange("/people/" + person.get().getId(), HttpMethod.GET,
				this.protectedHeader, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void GetByIdResponseStatusCode403() {

		Optional<People> person = Optional.of(new People());
		person.get().setId(new Long(100));
		person.get().setName("Pablo");
		person.get().setCpf("11220312622");
		person.get().setSexo("Masculino");
		person.get().setDateOfBirth(new Date(System.currentTimeMillis()));
		peopleService.save(person.get());

		BDDMockito.when(this.peopleService.findById(person.get().getId())).thenReturn(person);
		ResponseEntity<String> response = restTemplet.exchange("/people/" + person.get().getId(), HttpMethod.GET,
				this.wrongHeader, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(403);
	}

	@Test
	public void GetByIdResponseStatusCode404() {

		Optional<People> person = Optional.of(new People());
		person.get().setId(new Long(100));
		person.get().setName("Pablo");
		person.get().setCpf("11220312622");
		person.get().setSexo("Masculino");
		person.get().setDateOfBirth(new Date(System.currentTimeMillis()));
		peopleService.save(person.get());

		BDDMockito.when(this.peopleService.findById(person.get().getId())).thenReturn(person);
		ResponseEntity<String> response = restTemplet.exchange("/peoplef/" + person.get().getId(), HttpMethod.GET,
				this.protectedHeader, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void PostResponseStatusCode403() {

		Optional<People> person = Optional.of(new People());
		person.get().setId(new Long(100));
		person.get().setName("Pablo");
		person.get().setCpf("11220312622");
		person.get().setSexo("Masculino");
		person.get().setDateOfBirth(new Date(System.currentTimeMillis()));
		peopleService.save(person.get());

		ResponseEntity<People> response = restTemplet.exchange("/people", HttpMethod.POST,
				new HttpEntity<>(person, this.wrongHeader.getHeaders()), People.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(403);
	}

	@Test
	public void PostResponseStatusCode404() {

		Optional<People> person = Optional.of(new People());
		person.get().setId(new Long(100));
		person.get().setName("Pablo");
		person.get().setCpf("11220312622");
		person.get().setSexo("Masculino");
		person.get().setDateOfBirth(new Date(System.currentTimeMillis()));
		peopleService.save(person.get());

		ResponseEntity<People> response = restTemplet.exchange("/peoplef", HttpMethod.POST,
				new HttpEntity<>(person, this.protectedHeader.getHeaders()), People.class);

		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void DeleteResponseStatusCode404() {

		Optional<People> person = Optional.of(new People());
		person.get().setId(new Long(100));
		person.get().setName("Pablo");
		person.get().setCpf("11220312622");
		person.get().setSexo("Masculino");
		person.get().setDateOfBirth(new Date(System.currentTimeMillis()));
		peopleService.save(person.get());

		ResponseEntity<People> response = restTemplet.exchange("/peoplef", HttpMethod.DELETE,
				new HttpEntity<>(person, this.protectedHeader.getHeaders()), People.class);

		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void DeleteResponseStatusCode403() {

		Optional<People> person = Optional.of(new People());
		person.get().setId(new Long(100));
		person.get().setName("Pablo");
		person.get().setCpf("11220312622");
		person.get().setSexo("Masculino");
		person.get().setDateOfBirth(new Date(System.currentTimeMillis()));
		peopleService.save(person.get());

		ResponseEntity<People> response = restTemplet.exchange("/people", HttpMethod.DELETE,
				new HttpEntity<>(person, this.wrongHeader.getHeaders()), People.class);

		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(403);
	}

	@Test
	public void DeleteResponseStatusCode200() {

		Optional<People> person = Optional.of(new People());
		person.get().setId(new Long(100));
		person.get().setName("Pablo");
		person.get().setCpf("11220312622");
		person.get().setSexo("Masculino");
		person.get().setDateOfBirth(new Date(System.currentTimeMillis()));
		peopleService.save(person.get());

		BDDMockito.when(this.peopleService.findById(person.get().getId())).thenReturn(person);
		ResponseEntity<People> response = restTemplet.exchange("/people", HttpMethod.DELETE,
				new HttpEntity<>(person, this.protectedHeader.getHeaders()), People.class);

		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

	@Test
	public void DeleteByIdResponseStatusCode404() {

		Optional<People> person = Optional.of(new People());
		person.get().setId(new Long(100));
		person.get().setName("Pablo");
		person.get().setCpf("11220312622");
		person.get().setSexo("Masculino");
		person.get().setDateOfBirth(new Date(System.currentTimeMillis()));
		peopleService.save(person.get());

		BDDMockito.when(this.peopleService.findById(person.get().getId())).thenReturn(person);
		ResponseEntity<String> response = restTemplet.exchange("/peoplef/" + person.get().getId(), HttpMethod.DELETE,
				this.protectedHeader, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}

	@Test
	public void DeleteByIdResponseStatusCode200() {

		Optional<People> person = Optional.of(new People());
		person.get().setId(new Long(100));
		person.get().setName("Pablo");
		person.get().setCpf("11220312622");
		person.get().setSexo("Masculino");
		person.get().setDateOfBirth(new Date(System.currentTimeMillis()));
		peopleService.save(person.get());

		BDDMockito.when(this.peopleService.findById(person.get().getId())).thenReturn(person);
		ResponseEntity<String> response = restTemplet.exchange("/people/" + person.get().getId(), HttpMethod.DELETE,
				this.protectedHeader, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}

}
