package com.dev.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.app.model.Client;
import com.dev.app.service.ClientService;

@Controller
@RequestMapping("/clientes")
public class ClientController {

	Logger logger = LoggerFactory.getLogger(ClientController.class);

	@Autowired
	private ClientService clientService;

	@PostMapping("/crear")
	@ResponseBody
	public ResponseEntity<Client> save(@RequestBody Client obj) {
		try {
			Client tmp = clientService.save(obj);
			return ResponseEntity.ok(tmp);
		} catch (IllegalArgumentException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/editar")
	@ResponseBody
	public ResponseEntity<Client> edit(@RequestBody Client obj) {
		Client tmp = clientService.edit(obj);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(tmp);
		}
	}

	@PatchMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<Client> update(@RequestBody Client obj)
			throws IllegalArgumentException, IllegalAccessException {
		Client tmp = clientService.update(obj);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(tmp);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean tmp = clientService.delete(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(tmp);
		}
	}

	@GetMapping("/buscar/{id}")
	@ResponseBody
	public ResponseEntity<Client> findOne(@PathVariable Long id) {
		Client tmp = clientService.findOne(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(tmp);
		}
	}

}