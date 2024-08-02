package com.dev.app;

import java.nio.charset.StandardCharsets;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.dev.app.controller.ClientController;
import com.dev.app.model.Client;
import com.dev.app.repository.ClientRepository;
import com.dev.app.service.ClientService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(controllers = { ClientController.class })
public class ClientControllerTest {

	private Client client;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ClientService clientService;
	@MockBean
	private ClientRepository clientRepository;

	@Test
	public void findOne_okStatus() throws Exception {
		long id = 1L;
		client = new Client("user", "123", true);
		client.setId(id);
		client.setFullName("Test");
		Mockito.when(clientService.findOne(id)).thenReturn(client);
		mockMvc.perform(MockMvcRequestBuilders.get("/clientes/buscar/" + id))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON_VALUE));
		Mockito.verify(clientService, Mockito.times(1)).findOne(id);
	}

	@Test
	public void findOne_notFoundStatus() throws Exception {
		long id = 2L;
		client = null;
		Mockito.when(clientService.findOne(id)).thenReturn(null);
		mockMvc.perform(MockMvcRequestBuilders.get("/clientes/buscar/" + id))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
		Mockito.verify(clientService, Mockito.times(1)).findOne(id);
	}

	@Test
	public void edit_success() throws Exception {
		Client tmp = new Client("bryan", "123", true);
		tmp.setId(1L); //
		Mockito.when(clientService.edit(Mockito.any(Client.class))).thenReturn(tmp);
		String reqBody = new ObjectMapper().writeValueAsString(new Client());
		mockMvc.perform(MockMvcRequestBuilders.put("/clientes/editar").contentType(MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON_VALUE).characterEncoding(StandardCharsets.UTF_8).content(reqBody))
				.andExpect(MockMvcResultMatchers.status().isOk());
		Mockito.verify(clientService, Mockito.times(1)).edit(Mockito.any(Client.class));
	}

}
