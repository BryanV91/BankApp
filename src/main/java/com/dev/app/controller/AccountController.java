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

import com.dev.app.model.Account;
import com.dev.app.service.AccountService;

@Controller
@RequestMapping("/cuentas")
public class AccountController {

	Logger logger = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@PostMapping("/crear")
	@ResponseBody
	public ResponseEntity<Account> save(@RequestBody Account obj) {
		try {
			Account tmp = accountService.save(obj);
			return ResponseEntity.ok(tmp);
		} catch (IllegalArgumentException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/editar")
	@ResponseBody
	public ResponseEntity<Account> edit(@RequestBody Account obj) {
		try {
			Account tmp = accountService.edit(obj);
			return ResponseEntity.ok(tmp);
		} catch (IllegalArgumentException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PatchMapping("/actualizar")
	@ResponseBody
	public ResponseEntity<Account> update(@RequestBody Account obj)
			throws IllegalArgumentException, IllegalAccessException {
		try {
			Account tmp = accountService.update(obj);
			return ResponseEntity.ok(tmp);
		} catch (IllegalArgumentException ex) {
			logger.error(ex.getMessage());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/eliminar/{id}")
	@ResponseBody
	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
		Boolean tmp = accountService.delete(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(tmp);
		}
	}

	@GetMapping("/buscar/{id}")
	@ResponseBody
	public ResponseEntity<Account> findOne(@PathVariable Long id) {
		Account tmp = accountService.findOne(id);
		if (tmp == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return ResponseEntity.ok(tmp);
		}
	}

}