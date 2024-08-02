package com.dev.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.dev.app.model.Client;

@SpringBootTest(classes = TestApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
public class ClientControllerIntegrationTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Sql({ "classpath:schema.sql", "classpath:data.sql" })
	@Test
	public void testFindExistingClient() {
		assertTrue(this.restTemplate.getForObject("http://localhost:" + port + "/clientes/buscar/1", Client.class)
				.getClientId().equals("bryan91"));
	}

	@Test
	public void testCreateClient() {
		Client client = new Client("integration-test-" + UUID.randomUUID().toString(), "123", true);
		client.setCode("0000000000");
		client.setFullName("AutomationTest");
		ResponseEntity<Client> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/clientes/crear", client, Client.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}

}
