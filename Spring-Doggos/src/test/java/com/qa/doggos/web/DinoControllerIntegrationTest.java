package com.qa.doggos.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.doggos.domain.Doggo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT) // boots entire context on random port
@AutoConfigureMockMvc // Sets up MockMVC object
@Sql(scripts = { "classpath:dog-schema.sql",
		"classpath:dog-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class DinoControllerIntegrationTest {

	@Autowired // pulls the MockMVC object from the context
	private MockMvc mvc; // class that performs all the requests (like postman does)

	@Autowired
	private ObjectMapper mapper;

	@Test
	void testCreate() throws Exception {

		Doggo testDog = new Doggo(1, "Mega Noms", 5, "Sausage");
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = post("/create").contentType(MediaType.APPLICATION_JSON).content(testDogAsJSON);

		Doggo testDoggoCreated = new Doggo(1, "Mega Noms", 5, "Sausage");
		String testCreatedDoggoAsJSON = this.mapper.writeValueAsString(testDoggoCreated);

		ResultMatcher checkStatus = status().isCreated(); // checks is status 201 (Created)
		ResultMatcher checkBody = content().json(testCreatedDoggoAsJSON); // does the body match
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody); // Sends request, checks the status, checks
																			// the body
	}

	@Test
	void testFindAll() throws Exception {
		List<Doggo> testDog = List.of(new Doggo(1, "Mega Noms", 5, "Sausage"));
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = get("/getAll");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDogAsJSON); // does the body match
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}

	@Test
	void testFindByID() throws Exception {
		Doggo testDog = new Doggo(1, "Mega Noms", 5, "Sausage");
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = get("/get/1");

		ResultMatcher checkStatus = status().isOk();
		ResultMatcher checkBody = content().json(testDogAsJSON); // does the body match
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testReplaceDog() throws Exception {
		Doggo testDog = new Doggo(1, "Noms", 5, "Sausage");
		String testDogAsJSON = this.mapper.writeValueAsString(testDog);
		RequestBuilder req = put("/replace/1").contentType(MediaType.APPLICATION_JSON).content(testDogAsJSON);

		ResultMatcher checkStatus = status().isAccepted(); 
		ResultMatcher checkBody = content().json(testDogAsJSON); // does the body match
		this.mvc.perform(req).andExpect(checkStatus).andExpect(checkBody);
	}
	
	@Test
	void testDeleteDog() throws Exception {
	
		RequestBuilder req = delete("/delete/1").contentType(MediaType.APPLICATION_JSON);
		ResultMatcher checkStatus = status().isNoContent();
		this.mvc.perform(req).andExpect(checkStatus); 
	}

}
